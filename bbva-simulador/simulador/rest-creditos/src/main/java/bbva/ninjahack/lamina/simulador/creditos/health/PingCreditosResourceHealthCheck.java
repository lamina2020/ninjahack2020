// tag::adocPingCreditos[]
package bbva.ninjahack.lamina.simulador.creditos.health;

import bbva.ninjahack.lamina.simulador.creditos.CreditosResource;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Liveness
@ApplicationScoped
public class PingCreditosResourceHealthCheck implements HealthCheck {

    @Inject
    CreditosResource creditosResource;

    @Override
    public HealthCheckResponse call() {
        creditosResource.hello();
        return HealthCheckResponse.named("Ping Creditos REST Endpoint").up().build();
    }
}
// end::adocPingCreditos[]
