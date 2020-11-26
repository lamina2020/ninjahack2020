// tag::adocResource[]
package bbva.ninjahack.lamina.simulador.creditos;

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

@Path("/api/creditos")
@Produces(APPLICATION_JSON)
public class CreditosResource {

    private static final Logger LOGGER = Logger.getLogger(CreditosResource.class);

    @Inject
    CreditosService service;


    // tag::adocOpenAPI[]
    @Operation(summary = "Devuelve todos los creditos")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Creditos.class, type = SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "Sin creditos")
    // end::adocOpenAPI[]

    @GET
    public Response getAllCreditos() {
        List<Creditos> creditos = service.findAllCreditos();
        LOGGER.debug("Total number of creditos " + creditos);
        return Response.ok(creditos).build();
    }


    @GET
    @Path("/usuarios/{idusuario}")
    public Response getAllCreditosUsuario(int idusuario) {
        List<Creditos> creditos = service.findAllCreditosUsuario(idusuario);
        LOGGER.debug("Total number of creditos " + creditos);
        return Response.ok(creditos).build();
    }










    // tag::adocOpenAPI[]
    @Operation(summary = "Crea una cuenta creditos válida")
    @APIResponse(responseCode = "201", description = "La URL de la cuenta creditos creado", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = URI.class)))
    // end::adocOpenAPI[]

    @POST
    public Response createCreditos(
        @Valid Creditos creditos, @Context UriInfo uriInfo) {

        if (service.findCreditosByIBAN(creditos.iban) != null){
            LOGGER.debug("El cuenta creditos " + creditos.iban + " ya existe");
            return Response.status(Response.Status.CONFLICT).build();
        }
        creditos = service.persistCreditos(creditos);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(creditos.id));
        LOGGER.debug("New cuenta creditos created with URI " + builder.build().toString());
        return Response.created(builder.build()).build();
    }



    @GET
    @Path("/{id}")
    public Response getCreditos(
        // tag::adocOpenAPI[]
        @Parameter(description = "Identificador de Creditos", required = true)
        // end::adocOpenAPI[]
        @PathParam("id") Long id) {
        Creditos creditos = service.findCreditosById(id);
        if (creditos != null) {
            LOGGER.debug("Found creditos " + creditos);
            return Response.ok(creditos).build();
        } else {
            LOGGER.debug("No creditos found with id " + id);
            return Response.noContent().build();
        }
    }


// Prescindible

    @PUT
    public Response updateCreditos(
        @Valid Creditos creditos) {
        creditos = service.updateCreditos(creditos);
        LOGGER.debug("Creditos updated with new valued " + creditos);
        return Response.ok(creditos).build();
    }


    @DELETE
    @Path("/{id}")
    public Response deleteCreditos(
        @PathParam("id") Long id) {
        service.deleteCreditos(id);
        LOGGER.debug("Creditos deleted with " + id);
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
