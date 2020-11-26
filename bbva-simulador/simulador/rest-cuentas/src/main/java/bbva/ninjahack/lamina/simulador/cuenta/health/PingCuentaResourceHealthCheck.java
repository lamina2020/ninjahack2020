// tag::adocPingCuenta[]
package bbva.ninjahack.lamina.simulador.cuenta.health;

import bbva.ninjahack.lamina.simulador.cuenta.CuentaResource;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Liveness
@ApplicationScoped
public class PingCuentaResourceHealthCheck implements HealthCheck {

    @Inject
    CuentaResource cuentaResource;

    @Override
    public HealthCheckResponse call() {
        cuentaResource.hello();
        return HealthCheckResponse.named("Ping Cuenta REST Endpoint").up().build();
    }
}
// end::adocPingCuenta[]
