// tag::adocApplicationLifeCycle[]
package bbva.ninjahack.lamina.simulador.posicionglobal;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ProfileManager;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
class PosicionglobalApplicationLifeCycle {

    private static final Logger LOGGER = Logger.getLogger(PosicionglobalApplicationLifeCycle.class);

    // tag::adocStartupEvent[]
    void onStart(@Observes StartupEvent ev) {
        // tag::adocProfileManager[]
        LOGGER.infof("The application POSICIONGLOBAL is starting with profile `%s`", ProfileManager.getActiveProfile());
        // end::adocProfileManager[]
    }
    // end::adocStartupEvent[]

    void onStop(@Observes ShutdownEvent ev) {
        LOGGER.info("The application POSICIONGLOBAL is stopping...");
    }
}
// end::adocApplicationLifeCycle[]
