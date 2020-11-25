// tag::adocPingUsuario[]
package bbva.ninjahack.lamina.simulador.usuario.health;

import bbva.ninjahack.lamina.simulador.usuario.UsuarioResource;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Liveness
@ApplicationScoped
public class PingUsuarioResourceHealthCheck implements HealthCheck {

    @Inject
    UsuarioResource usuarioResource;

    @Override
    public HealthCheckResponse call() {
        usuarioResource.hello();
        return HealthCheckResponse.named("Ping Usuario REST Endpoint").up().build();
    }
}
// end::adocPingUsuario[]
