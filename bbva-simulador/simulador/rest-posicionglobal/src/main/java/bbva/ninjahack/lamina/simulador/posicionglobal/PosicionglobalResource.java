// tag::adocResource[]
package bbva.ninjahack.lamina.simulador.posicionglobal;

// end::adocResource[]
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
// tag::adocResource[]
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

import java.util.List;
import bbva.ninjahack.lamina.simulador.posicionglobal.client.Cuenta;

import javax.enterprise.context.RequestScoped;
import javax.annotation.security.RolesAllowed;

import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/api/posicionglobal")
@RequestScoped
@Produces(APPLICATION_JSON)
public class PosicionglobalResource {

    private static final Logger LOGGER = Logger.getLogger(PosicionglobalResource.class);

    @Inject
    PosicionglobalService service;

    @Inject
    JsonWebToken jwt;


    @Operation(summary = "Devuelve todas las cuentas de un usuario")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Cuenta.class, type = SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "Sin cuentas")
    @RolesAllowed({ "User", "Admin" })
    @GET
    public Response getAllCuentas() {
        int idUsuario=Integer.parseInt(jwt.getClaim("idUsuario").toString());
        List<Cuenta> cuentas = service.findAllCuentas(idUsuario);
        LOGGER.debug("Numero total de cuentas del usuario " + idUsuario+ ": " + cuentas.size());
        return Response.ok(cuentas).build();
    }

    @GET
    @Produces(TEXT_PLAIN)
    @Path("/hello")
    public String hello() {
        return "hello";
    }
}
// end::adocResource[]
