// tag::adocResource[]
package bbva.ninjahack.lamina.simulador.cuenta;

// end::adocResource[]
// tag::adocMetricsImports[]
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
// end::adocMetricsImports[]
// tag::adocOpenAPIImports[]
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
// end::adocOpenAPIImports[]
// tag::adocResource[]
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;

@Path("/api/cuentas")
@Produces(APPLICATION_JSON)
public class CuentaResource {

    private static final Logger LOGGER = Logger.getLogger(CuentaResource.class);

    @Inject
    CuentaService service;


    // tag::adocOpenAPI[]
    @Operation(summary = "Devuelve todos los cuentas")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Cuenta.class, type = SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "Sin cuentas")
    // end::adocOpenAPI[]

    @GET
    public Response getAllCuentas() {
        List<Cuenta> cuentas = service.findAllCuentas();
        LOGGER.debug("Total number of cuentas " + cuentas);
        return Response.ok(cuentas).build();
    }


    @GET
    @Path("/usuarios/{idusuario}")
    public Response getAllCuentasUsuario(int idusuario) {
        List<Cuenta> cuentas = service.findAllCuentasUsuario(idusuario);
        LOGGER.debug("Total number of cuentas " + cuentas);
        return Response.ok(cuentas).build();
    }










    // tag::adocOpenAPI[]
    @Operation(summary = "Crea una cuenta válida")
    @APIResponse(responseCode = "201", description = "La URL de la cuenta creado", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = URI.class)))
    // end::adocOpenAPI[]

    @POST
    public Response createCuenta(
        @Valid Cuenta cuenta, @Context UriInfo uriInfo) {

        if (service.findCuentaByIBAN(cuenta.iban) != null){
            LOGGER.debug("El cuenta " + cuenta.iban + " ya existe");
            return Response.status(Response.Status.CONFLICT).build();
        }
        cuenta = service.persistCuenta(cuenta);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(cuenta.iban));
        LOGGER.debug("New cuenta created with URI " + builder.build().toString());
        return Response.created(builder.build()).build();
    }


    // tag::adocOpenAPI[]
    @Operation(summary = "Permite obtener una cuenta, si el id es correcto")
    @APIResponse(responseCode = "201", description = "Token JWT")
    @APIResponse(responseCode = "204", description = "Cuenta no encontrada")
    // end::adocOpenAPI[]

    // tag::adocOpenAPI[]
    @Operation(summary = "Devuelve un cuenta dado un identificador")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Cuenta.class)))
    @APIResponse(responseCode = "204", description = "No se ha encontrado un cuenta para el identificador")
    // end::adocOpenAPI[]

    @GET
    @Path("/{id}")
    public Response getCuenta(
        // tag::adocOpenAPI[]
        @Parameter(description = "Identificador de cuenta", required = true)
        // end::adocOpenAPI[]
        @PathParam("id") Long id) {
        Cuenta cuenta = service.findCuentaById(id);
        if (cuenta != null) {
            LOGGER.debug("Found cuenta " + cuenta);
            return Response.ok(cuenta).build();
        } else {
            LOGGER.debug("No cuenta found with id " + id);
            return Response.noContent().build();
        }
    }


// Prescindible

    @PUT
    public Response updateCuenta(
        @Valid Cuenta cuenta) {
        cuenta = service.updateCuenta(cuenta);
        LOGGER.debug("Cuenta updated with new valued " + cuenta);
        return Response.ok(cuenta).build();
    }


    @DELETE
    @Path("/{id}")
    public Response deleteCuenta(
        @PathParam("id") Long id) {
        service.deleteCuenta(id);
        LOGGER.debug("Cuenta deleted with " + id);
        return Response.noContent().build();
    }

    @GET
    @Produces(TEXT_PLAIN)
    @Path("/hello")
    public String hello() {
        return "hello";
    }
}
// end::adocResource[]
