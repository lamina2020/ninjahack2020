// tag::adocDatabaseConnection[]
package bbva.ninjahack.lamina.simulador.cuenta.health;

import bbva.ninjahack.lamina.simulador.cuenta.Cuenta;
import bbva.ninjahack.lamina.simulador.cuenta.CuentaService;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@Readiness
@ApplicationScoped
public class DatabaseConnectionHealthCheck implements HealthCheck {

    @Inject
    CuentaService cuentaService;

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse
            .named("Cuenta Datasource connection health check");

        try {
            List<Cuenta> cuentas = cuentaService.findAllCuentas();
            responseBuilder.withData("Number of cuentas in the database", cuentas.size()).up();
        } catch (IllegalStateException e) {
            responseBuilder.down();
        }

        return responseBuilder.build();
    }
}
// end::adocDatabaseConnection[]
