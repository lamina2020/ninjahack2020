// tag::adocPingUsuario[]
package io.quarkus.workshop.superheroes.hero.health;

import io.quarkus.workshop.superheroes.hero.HeroResource;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Liveness
@ApplicationScoped
public class PingUsuarioResourceHealthCheck implements HealthCheck {

    @Inject
    UsuarioResource usuarioResource;

    @Override
    public HealthCheckResponse call() {
        usuarioResource.hello();
        return HealthCheckResponse.named("Ping Usuario REST Endpoint").up().build();
    }
}
// end::adocPingUsuario[]
