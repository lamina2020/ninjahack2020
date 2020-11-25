// tag::adocResource[]
package io.quarkus.workshop.superheroes.hero;

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

@Path("/api/usuarios")
@Produces(APPLICATION_JSON)
public class UsuarioResource {

    private static final Logger LOGGER = Logger.getLogger(UsuarioResource.class);

    @Inject
    UsuarioService service;

    @GET
    public Response getAllUsuarios() {
        List<Usuario> usuarios = service.findAllUsuarios();
        LOGGER.debug("Total number of usuarios " + usuarios);
        return Response.ok(usuarios).build();
    }


// Registro usuario

    @POST
    public Response createUsuario(
        @Valid Usuario usuario, @Context UriInfo uriInfo) {
        usuario = service.persistUsuario(usuario);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(usuario.id));
        LOGGER.debug("New usuario created with URI " + builder.build().toString());
        return Response.created(builder.build()).build();
    }

//Login

    // TODO Buscar respuesta correcta para password erroneo
    @POST
    @Path("/login")
    public Response loginUsuario(
        @Valid Usuario usuario, @Context UriInfo uriInfo) {
        usuarioBBDD = service.findUsuarioByEmail(usuario.email);

        if(usuarioBBDD != null){
            if (usuario.password.equals(usuarioBBDD.password)){
                LOGGER.debug("Credenciales del usuario "+usuario.email+" correctas");
                return Response.ok(usuario).build();
            } else{
                LOGGER.debug("Credenciales del usuario "+usuario.email+" incorrectas");
                return Response.noContent().build();
            }
        } else{
            LOGGER.debug("Usuario "+usuario.email+" no encontrado");
            return Response.noContent().build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getUsuario(
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
