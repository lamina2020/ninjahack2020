// tag::adocResource[]
package bbva.ninjahack.lamina.simulador.tarjeta;

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

@Path("/api/tarjetas")
@Produces(APPLICATION_JSON)
public class TarjetaResource {

    private static final Logger LOGGER = Logger.getLogger(TarjetaResource.class);

    @Inject
    TarjetaService service;


    // tag::adocOpenAPI[]
    @Operation(summary = "Devuelve todos los tarjetas")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Tarjeta.class, type = SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "Sin tarjetas")
    // end::adocOpenAPI[]

    @GET
    public Response getAllTarjetas() {
        List<Tarjeta> tarjetas = service.findAllTarjetas();
        LOGGER.debug("Total number of tarjetas " + tarjetas);
        return Response.ok(tarjetas).build();
    }


    @GET
    @Path("/usuarios/{idusuario}")
    public Response getAllTarjetasUsuario(int idusuario) {
        List<Tarjeta> tarjetas = service.findAllTarjetasUsuario(idusuario);
        LOGGER.debug("Total number of tarjetas " + tarjetas);
        return Response.ok(tarjetas).build();
    }


    // tag::adocOpenAPI[]
    @Operation(summary = "Crea una tarjeta válida")
    @APIResponse(responseCode = "201", description = "La URL de la tarjeta creado", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = URI.class)))
    // end::adocOpenAPI[]

    @POST
    public Response createTarjeta(
        @Valid Tarjeta tarjeta, @Context UriInfo uriInfo) {

        if (service.findTarjetaByPAN(tarjeta.pan) != null){
            LOGGER.debug("La tarjeta " + tarjeta.pan + " ya existe");
            return Response.status(Response.Status.CONFLICT).build();
        }
        tarjeta = service.persistTarjeta(tarjeta);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(tarjeta.id));
        LOGGER.debug("New tarjeta created with URI " + builder.build().toString());
        return Response.created(builder.build()).build();
    }



    @GET
    @Path("/{id}")
    public Response getTarjeta(
        // tag::adocOpenAPI[]
        @Parameter(description = "Identificador de tarjeta", required = true)
        // end::adocOpenAPI[]
        @PathParam("id") Long id) {
        Tarjeta tarjeta = service.findTarjetaById(id);
        if (tarjeta != null) {
            LOGGER.debug("Found tarjeta " + tarjeta);
            return Response.ok(tarjeta).build();
        } else {
            LOGGER.debug("No tarjeta found with id " + id);
            return Response.noContent().build();
        }
    }


// Prescindible

    @PUT
    public Response updateTarjeta(
        @Valid Tarjeta tarjeta) {
        tarjeta = service.updateTarjeta(tarjeta);
        LOGGER.debug("Tarjeta updated with new valued " + tarjeta);
        return Response.ok(tarjeta).build();
    }


    @DELETE
    @Path("/{id}")
    public Response deleteTarjeta(
        @PathParam("id") Long id) {
        service.deleteTarjeta(id);
        LOGGER.debug("Tarjeta deleted with " + id);
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
