// tag::adocDatabaseConnection[]
package bbva.ninjahack.lamina.simulador.tarjeta.health;

import bbva.ninjahack.lamina.simulador.tarjeta.Tarjeta;
import bbva.ninjahack.lamina.simulador.tarjeta.TarjetaService;
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
    TarjetaService tarjetaService;

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse
            .named("Tarjeta Datasource connection health check");

        try {
            List<Tarjeta> tarjetas = tarjetaService.findAllTarjetas();
            responseBuilder.withData("Number of tarjetas in the database", tarjetas.size()).up();
        } catch (IllegalStateException e) {
            responseBuilder.down();
        }

        return responseBuilder.build();
    }
}
// end::adocDatabaseConnection[]
