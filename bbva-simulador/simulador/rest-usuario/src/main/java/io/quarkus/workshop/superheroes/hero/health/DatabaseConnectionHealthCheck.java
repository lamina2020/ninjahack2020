// tag::adocDatabaseConnection[]
package io.quarkus.workshop.superheroes.hero.health;

import io.quarkus.workshop.superheroes.hero.Hero;
import io.quarkus.workshop.superheroes.hero.HeroService;
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
    UsuarioService usuarioService;

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse
            .named("Usuario Datasource connection health check");

        try {
            List<Usuario> usuarios = usuarioService.findAllUsuarios();
            responseBuilder.withData("Number of usuarios in the database", usuarios.size()).up();
        } catch (IllegalStateException e) {
            responseBuilder.down();
        }

        return responseBuilder.build();
    }
}
// end::adocDatabaseConnection[]
