package bbva.ninjahack.lamina.simulador.posicionglobal;

import bbva.ninjahack.lamina.simulador.posicionglobal.client.Cuenta;
import bbva.ninjahack.lamina.simulador.posicionglobal.client.CuentaService;

//import io.smallrye.reactive.messaging.annotations.Channel;
//import io.smallrye.reactive.messaging.annotations.Emitter;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.rest.client.inject.RestClient;
// tag::adocTransactional[]
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Random;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@ApplicationScoped
@Transactional(SUPPORTS)
public class PosicionglobalService {

    // tag::adocRestClient[]
    @Inject
    @RestClient
    CuentaService cuentaService;

    // end::adocRestClient[]


    private static final Logger LOGGER = Logger.getLogger(PosicionglobalService.class);


    public List<Cuenta> findAllCuentas(int idUsuario) {
        return cuentaService.getAllCuentas(idUsuario);
    }

}
// end::adocTransactional[]
