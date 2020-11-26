// tag::adocApplicationLifeCycle[]
package bbva.ninjahack.lamina.simulador.valores;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ProfileManager;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
class ValoresApplicationLifeCycle {

    private static final Logger LOGGER = Logger.getLogger(ValoresApplicationLifeCycle.class);

    // tag::adocStartupEvent[]
    void onStart(@Observes StartupEvent ev) {
        // tag::adocProfileManager[]
        LOGGER.infof("The application VALORES is starting with profile `%s`", ProfileManager.getActiveProfile());
        // end::adocProfileManager[]
    }
    // end::adocStartupEvent[]

    void onStop(@Observes ShutdownEvent ev) {
        LOGGER.info("The application VALORES is stopping...");
    }
}
// end::adocApplicationLifeCycle[]
