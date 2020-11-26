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

    @Operation(summary = "Devuelve todas las tarjetas de un usuario")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Tarjeta.class, type = SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "Sin tarjetas")
    @RolesAllowed({ "User", "Admin" })
    @GET
    public Response getAllTarjetas() {
        int idUsuario=Integer.parseInt(jwt.getClaim("idUsuario").toString());
        List<Tarjeta> tarjetas = service.findAllTarjetas(idUsuario);
        LOGGER.debug("Numero total de tarjetas del usuario " + idUsuario+ ": " + tarjetas.size());
        return Response.ok(tarjetas).build();
    }

    @Operation(summary = "Devuelve todas los valores de un usuario")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Valor.class, type = SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "Sin valores de Bolsa")
    @RolesAllowed({ "User", "Admin" })
    @GET
    public Response getAllValores() {
        int idUsuario=Integer.parseInt(jwt.getClaim("idUsuario").toString());
        List<Valor> valores = service.findAllValores(idUsuario);
        LOGGER.debug("Numero total de valores de Bolsa del usuario " + idUsuario+ ": " + valores.size());
        return Response.ok(valores).build();
    }

    @Operation(summary = "Devuelve todas los creditos de un usuario")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Credito.class, type = SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "Sin creditos bancarios")
    @RolesAllowed({ "User", "Admin" })
    @GET
    public Response getAllCreditos() {
        int idUsuario=Integer.parseInt(jwt.getClaim("idUsuario").toString());
        List<Credito> creditos = service.findAllCreditos(idUsuario);
        LOGGER.debug("Numero total de creditos del usuario " + idUsuario+ ": " + creditos.size());
        return Response.ok(creditos).build();
    }


    @GET
    @Produces(TEXT_PLAIN)
    @Path("/hello")
    public String hello() {
        return "hello";
    }
}
// end::adocResource[]
