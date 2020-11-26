// tag::adocTransactional[]
package bbva.ninjahack.lamina.simulador.valores;

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
public class ValoresService {

    @Transactional(SUPPORTS)
    public List<Valores> findAllValores() {
        return Valores.listAll();
    }

    @Transactional(SUPPORTS)
    public List<Valores> findAllValoresUsuario(int idusuario) {
        return Valores.listAll(idusuario);
    }

    @Transactional(SUPPORTS)
    public Valores findValoresById(Long id) {
        return Valores.findById(id);
    }

    @Transactional(SUPPORTS)
    public Valores findValoresByIBAN(String iban) {
        return Valores.findByIBAN(iban);
    }


    // tag::adocPersistCuenta[]
    public Valores persistValores(@Valid Valores valores) {
        Valores.persist(valores);
        return valores;
    }
    // end::adocPersistCuenta[]

    public Valores updateValores(@Valid Valores valores) {
        Valores entity = Valores.findById(valores.id);
        entity.iban = valores.iban;
        entity.riesgo = valores.riesgo;
        entity.interes=valores.interes;

        return entity;
    }

    public void deleteValores(Long id) {
        Valores valores = Valores.findById(id);
        valores.delete();
    }
}
// end::adocTransactional[]
