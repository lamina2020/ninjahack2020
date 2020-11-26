// tag::adocDatabaseConnection[]
package bbva.ninjahack.lamina.simulador.creditos.health;

import bbva.ninjahack.lamina.simulador.creditos.Creditos;
import bbva.ninjahack.lamina.simulador.creditos.CreditosService;
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
    CreditosService creditosService;

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse
            .named("Creditos Datasource connection health check");

        try {
            List<Creditos> creditos = creditosService.findAllCreditos();
            responseBuilder.withData("Number of creditos in the database", creditos.size()).up();
        } catch (IllegalStateException e) {
            responseBuilder.down();
        }

        return responseBuilder.build();
    }
}
// end::adocDatabaseConnection[]
