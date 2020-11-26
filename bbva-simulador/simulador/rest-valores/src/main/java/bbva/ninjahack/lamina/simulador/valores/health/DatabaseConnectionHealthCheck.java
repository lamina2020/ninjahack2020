// tag::adocDatabaseConnection[]
package bbva.ninjahack.lamina.simulador.valores.health;

import bbva.ninjahack.lamina.simulador.valores.Valores;
import bbva.ninjahack.lamina.simulador.valores.ValoresService;
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
    ValoresService valoresService;

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse
            .named("Valores Datasource connection health check");

        try {
            List<Valores> valores = valoresService.findAllValores();
            responseBuilder.withData("Number of valores in the database", valores.size()).up();
        } catch (IllegalStateException e) {
            responseBuilder.down();
        }

        return responseBuilder.build();
    }
}
// end::adocDatabaseConnection[]
