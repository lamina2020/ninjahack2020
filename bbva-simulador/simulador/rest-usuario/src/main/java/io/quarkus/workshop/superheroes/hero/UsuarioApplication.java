// tag::adocApplication[]
package io.quarkus.workshop.superheroes.hero;

import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
@OpenAPIDefinition(
    info = @Info(title = "Usuario API",
        description = "This API allows CRUD operations on a usuario",
        version = "1.0",
        contact = @Contact(name = "La Mina", url = "https://github.com/lamina2020")),
    servers = {
        @Server(url = "http://localhost:8083")
    },
    externalDocs = @ExternalDocumentation(url = "https://github.com/lamina2020/ninjahack", description = "Proyectos de La Mina"),
    tags = {
        @Tag(name = "api", description = "Public that can be used by anybody"),
        @Tag(name = "usuarios", description = "Anybody interested in usuarios")
    }
)
public class UsuarioUApplication extends Application {
}
// end::adocApplication[]
