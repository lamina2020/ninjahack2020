// tag::adocPingPosicionglobal[]
package bbva.ninjahack.lamina.simulador.posicionglobal.health;

import bbva.ninjahack.lamina.simulador.posicionglobal.PosicionglobalResource;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Liveness
@ApplicationScoped
public class PingPosicionglobalResourceHealthCheck implements HealthCheck {

    @Inject
    PosicionglobalResource posicionglobalResource;

    @Override
    public HealthCheckResponse call() {
        posicionglobalResource.hello();
        return HealthCheckResponse.named("Ping Posicionglobal REST Endpoint").up().build();
    }
}
// end::adocPingPosicionglobal[]
