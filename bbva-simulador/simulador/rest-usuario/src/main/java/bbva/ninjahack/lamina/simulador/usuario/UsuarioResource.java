// tag::adocResource[]
package bbva.ninjahack.lamina.simulador.usuario;

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

@Path("/api/usuarios")
@Produces(APPLICATION_JSON)
public class UsuarioResource {

    private static final Logger LOGGER = Logger.getLogger(UsuarioResource.class);

    @Inject
    UsuarioService service;


    // tag::adocOpenAPI[]
    @Operation(summary = "Devuelve todos los usuarios")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Usuario.class, type = SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "Sin usuarios")
    // end::adocOpenAPI[]
    @GET
    public Response getAllUsuarios() {
        List<Usuario> usuarios = service.findAllUsuarios();
        LOGGER.debug("Total number of usuarios " + usuarios);
        return Response.ok(usuarios).build();
    }


    // tag::adocOpenAPI[]
    @Operation(summary = "Crea un usuario válido")
    @APIResponse(responseCode = "201", description = "La URL del usuario creado", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = URI.class)))
    // end::adocOpenAPI[]
    @POST
    public Response createUsuario(
        @Valid Usuario usuario, @Context UriInfo uriInfo) {

        if (service.findUsuarioByEmail(usuario.email) != null){
            LOGGER.debug("El usuario " + usuario.email + " ya existe");
            return Response.status(Response.Status.CONFLICT).build();
        }
        usuario = service.persistUsuario(usuario);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(usuario.id));
        LOGGER.debug("New usuario created with URI " + builder.build().toString());
        return Response.created(builder.build()).build();
    }


    // tag::adocOpenAPI[]
    @Operation(summary = "Permite logarse a un usuario, si la contraseña es correcta")
    @APIResponse(responseCode = "201", description = "Token JWT")
    @APIResponse(responseCode = "204", description = "Usuario no encontrado")
    @APIResponse(responseCode = "401", description = "Contraseña incorrecta")
    // end::adocOpenAPI[]
    @POST
    @Path("/login")
    public Response loginUsuario(
        // tag::adocOpenAPI[]
        @Parameter(description = "Usuario introducido en página de login", required = true)
        // end::adocOpenAPI[]
        @Valid Usuario usuario, @Context UriInfo uriInfo) {
        Usuario usuarioBBDD = service.findUsuarioByEmail(usuario.email);

        if(usuarioBBDD != null){
            if (usuario.password.equals(usuarioBBDD.password)){
                LOGGER.debug("Credenciales del usuario "+usuario.email+" correctas");

                // Creación de token JWT
                JwtClaimsBuilder builder1 = Jwt.claims();
                builder1.claim("email", usuario.email);
                String token=builder1.sign();

                return Response.ok(token).build();
            } else{
                LOGGER.debug("Credenciales del usuario "+usuario.email+" incorrectas");
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } else{
            LOGGER.debug("Usuario "+usuario.email+" no encontrado");
            return Response.noContent().build();
        }
    }


    // tag::adocOpenAPI[]
    @Operation(summary = "Devuelve un usuario dado un identificador")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Usuario.class)))
    @APIResponse(responseCode = "204", description = "No se ha encontrado un usuario para el identificador")
    // end::adocOpenAPI[]
    @GET
    @Path("/{id}")
    public Response getUsuario(
        // tag::adocOpenAPI[]
        @Parameter(description = "Identificador de usuario", required = true)
        // end::adocOpenAPI[]
        @PathParam("id") Long id) {
        Usuario usuario = service.findUsuarioById(id);
        if (usuario != null) {
            LOGGER.debug("Found usuario " + usuario);
            return Response.ok(usuario).build();
        } else {
            LOGGER.debug("No usuario found with id " + id);
            return Response.noContent().build();
        }
    }


// Prescindible

    @PUT
    public Response updateUsuario(
        @Valid Usuario usuario) {
        usuario = service.updateUsuario(usuario);
        LOGGER.debug("Usuario updated with new valued " + usuario);
        return Response.ok(usuario).build();
    }


    @DELETE
    @Path("/{id}")
    public Response deleteUsuario(
        @PathParam("id") Long id) {
        service.deleteUsuario(id);
        LOGGER.debug("Usuario deleted with " + id);
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
