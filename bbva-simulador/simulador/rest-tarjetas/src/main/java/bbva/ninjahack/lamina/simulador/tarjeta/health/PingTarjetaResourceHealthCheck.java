// tag::adocPingCuenta[]
package bbva.ninjahack.lamina.simulador.tarjeta.health;

import bbva.ninjahack.lamina.simulador.tarjeta.TarjetaResource;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Liveness
@ApplicationScoped
public class PingTarjetaResourceHealthCheck implements HealthCheck {

    @Inject
    TarjetaResource tarjetaResource;

    @Override
    public HealthCheckResponse call() {
        tarjetaResource.hello();
        return HealthCheckResponse.named("Ping Tarjeta REST Endpoint").up().build();
    }
}
// end::adocPingCuenta[]
