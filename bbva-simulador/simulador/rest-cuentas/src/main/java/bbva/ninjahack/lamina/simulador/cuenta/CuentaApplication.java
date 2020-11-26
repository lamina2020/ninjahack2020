// tag::adocApplication[]
package bbva.ninjahack.lamina.simulador.cuenta;

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
    info = @Info(title = "Cuenta API",
        description = "This API allows CRUD operations on a cuenta",
        version = "1.0",
        contact = @Contact(name = "La Mina", url = "https://github.com/lamina2020")),
//    servers = {
//        @Server(url = "http://localhost:8083")
//    },
    externalDocs = @ExternalDocumentation(url = "https://github.com/lamina2020/ninjahack", description = "Proyectos de La Mina"),
    tags = {
        @Tag(name = "api", description = "Public that can be used by anybody"),
        @Tag(name = "cuentas", description = "Anybody interested in cuentas")
    }
)
public class CuentaApplication extends Application {
}
// end::adocApplication[]
