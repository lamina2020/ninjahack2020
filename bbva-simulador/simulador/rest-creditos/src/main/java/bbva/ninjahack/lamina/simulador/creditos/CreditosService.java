// tag::adocTransactional[]
package bbva.ninjahack.lamina.simulador.creditos;

// end::adocTransactional[]
import org.eclipse.microprofile.config.inject.ConfigProperty;
// tag::adocTransactional[]
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@ApplicationScoped
@Transactional(REQUIRED)
public class CreditosService {

    @Transactional(SUPPORTS)
    public List<Creditos> findAllCreditos() {
        return Creditos.listAll();
    }

    @Transactional(SUPPORTS)
    public List<Creditos> findAllCreditosUsuario(int idusuario) {
        return Creditos.listAll(idusuario);
    }

    @Transactional(SUPPORTS)
    public Creditos findCreditosById(Long id) {
        return Creditos.findById(id);
    }

    @Transactional(SUPPORTS)
    public Creditos findCreditosByIBAN(String iban) {
        return Creditos.findByIBAN(iban);
    }


    // tag::adocPersistCreditos[]
    public Creditos persistCreditos(@Valid Creditos creditos) {
        Creditos.persist(creditos);
        return creditos;
    }
    // end::adocPersistCreditos[]

    public Creditos updateCreditos(@Valid Creditos creditos) {
        Creditos entity = Creditos.findById(creditos.id);
        entity.iban = creditos.iban;
        entity.importe=creditos.importe;

        return entity;
    }

    public void deleteCreditos(Long id) {
        Creditos creditos = Creditos.findById(id);
        creditos.delete();
    }
}
// end::adocTransactional[]
