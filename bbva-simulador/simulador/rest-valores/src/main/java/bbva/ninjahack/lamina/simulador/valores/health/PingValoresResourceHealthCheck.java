// tag::adocPingCuenta[]
package bbva.ninjahack.lamina.simulador.valores.health;

import bbva.ninjahack.lamina.simulador.valores.ValoresResource;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Liveness
@ApplicationScoped
public class PingValoresResourceHealthCheck implements HealthCheck {

    @Inject
    ValoresResource valoresResource;

    @Override
    public HealthCheckResponse call() {
        valoresResource.hello();
        return HealthCheckResponse.named("Ping Valores REST Endpoint").up().build();
    }
}
// end::adocPingCuenta[]
