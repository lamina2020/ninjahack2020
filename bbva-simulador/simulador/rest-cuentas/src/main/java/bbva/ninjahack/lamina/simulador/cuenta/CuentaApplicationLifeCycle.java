// tag::adocApplicationLifeCycle[]
package bbva.ninjahack.lamina.simulador.cuenta;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ProfileManager;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
class CuentaApplicationLifeCycle {

    private static final Logger LOGGER = Logger.getLogger(CuentaApplicationLifeCycle.class);

    // tag::adocStartupEvent[]
    void onStart(@Observes StartupEvent ev) {
        // tag::adocProfileManager[]
        LOGGER.infof("The application CUENTA is starting with profile `%s`", ProfileManager.getActiveProfile());
        // end::adocProfileManager[]
    }
    // end::adocStartupEvent[]

    void onStop(@Observes ShutdownEvent ev) {
        LOGGER.info("The application CUENTA is stopping...");
    }
}
// end::adocApplicationLifeCycle[]
