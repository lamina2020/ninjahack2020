// tag::adocResource[]
package bbva.ninjahack.lamina.simulador.valores;

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

@Path("/api/valores")
@Produces(APPLICATION_JSON)
public class ValoresResource {

    private static final Logger LOGGER = Logger.getLogger(ValoresResource.class);

    @Inject
    ValoresService service;


    // tag::adocOpenAPI[]
    @Operation(summary = "Devuelve todos los valores")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Valores.class, type = SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "Sin valores")
    // end::adocOpenAPI[]

    @GET
    public Response getAllValores() {
        List<Valores> valores = service.findAllValores();
        LOGGER.debug("Total number of valores " + valores);
        return Response.ok(valores).build();
    }


    @GET
    @Path("/usuarios/{idusuario}")
    public Response getAllValoresUsuario(int idusuario) {
        List<Valores> valores = service.findAllValoresUsuario(idusuario);
        LOGGER.debug("Total number of valores " + valores);
        return Response.ok(valores).build();
    }










    // tag::adocOpenAPI[]
    @Operation(summary = "Crea una cuenta valores válida")
    @APIResponse(responseCode = "201", description = "La URL de la cuenta valores creado", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = URI.class)))
    // end::adocOpenAPI[]

    @POST
    public Response createValores(
        @Valid Valores valores, @Context UriInfo uriInfo) {

        if (service.findValoresByIBAN(valores.iban) != null){
            LOGGER.debug("El cuenta valores " + valores.iban + " ya existe");
            return Response.status(Response.Status.CONFLICT).build();
        }
        valores = service.persistValores(valores);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(valores.id));
        LOGGER.debug("New cuenta valores created with URI " + builder.build().toString());
        return Response.created(builder.build()).build();
    }



    @GET
    @Path("/{id}")
    public Response getValores(
        // tag::adocOpenAPI[]
        @Parameter(description = "Identificador de Valores", required = true)
        // end::adocOpenAPI[]
        @PathParam("id") Long id) {
        Valores valores = service.findValoresById(id);
        if (valores != null) {
            LOGGER.debug("Found valores " + valores);
            return Response.ok(valores).build();
        } else {
            LOGGER.debug("No valores found with id " + id);
            return Response.noContent().build();
        }
    }


// Prescindible

    @PUT
    public Response updateValores(
        @Valid Valores valores) {
        valores = service.updateValores(valores);
        LOGGER.debug("Valores updated with new valued " + valores);
        return Response.ok(valores).build();
    }


    @DELETE
    @Path("/{id}")
    public Response deleteValores(
        @PathParam("id") Long id) {
        service.deleteValores(id);
        LOGGER.debug("Valores deleted with " + id);
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
