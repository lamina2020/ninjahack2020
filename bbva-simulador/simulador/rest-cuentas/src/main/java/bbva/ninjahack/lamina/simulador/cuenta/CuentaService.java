// tag::adocTransactional[]
package bbva.ninjahack.lamina.simulador.cuenta;

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
public class CuentaService {

    @Transactional(SUPPORTS)
    public List<Cuenta> findAllCuentas() {
        return Cuenta.listAll();
    }

    @Transactional(SUPPORTS)
    public List<Cuenta> findAllCuentasUsuario(int idusuario) {
        return Cuenta.listAll(idusuario);
    }

    @Transactional(SUPPORTS)
    public Cuenta findCuentaById(Long id) {
        return Cuenta.findById(id);
    }

    @Transactional(SUPPORTS)
    public Cuenta findCuentaByIBAN(String iban) {
        return Cuenta.findByIBAN(iban);
    }


    // tag::adocPersistCuenta[]
    public Cuenta persistCuenta(@Valid Cuenta cuenta) {
        Cuenta.persist(cuenta);
        return cuenta;
    }
    // end::adocPersistCuenta[]

    public Cuenta updateCuenta(@Valid Cuenta cuenta) {
        Cuenta entity = Cuenta.findById(cuenta.id);
        entity.iban = cuenta.iban;
        entity.saldo = cuenta.saldo;

        return entity;
    }

    public void deleteCuenta(Long id) {
        Cuenta cuenta = Cuenta.findById(id);
        cuenta.delete();
    }
}
// end::adocTransactional[]
