// tag::adocTransactional[]
package bbva.ninjahack.lamina.simulador.tarjeta;

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
public class TarjetaService {

    @Transactional(SUPPORTS)
    public List<Tarjeta> findAllTarjetas() {
        return Tarjeta.listAll();
    }

    @Transactional(SUPPORTS)
    public List<Tarjeta> findAllTarjetasUsuario(int idusuario) {
        return Tarjeta.listAll(idusuario);
    }

    @Transactional(SUPPORTS)
    public Tarjeta findTarjetaById(Long id) {
        return Tarjeta.findById(id);
    }

    @Transactional(SUPPORTS)
    public Tarjeta findTarjetaByPAN(String pan) {
        return Tarjeta.findByPAN(pan);
    }


    // tag::adocPersistTarjeta[]
    public Tarjeta persistTarjeta(@Valid Tarjeta tarjeta) {
        Tarjeta.persist(tarjeta);
        return tarjeta;
    }
    // end::adocPersistTarjeta[]

    public Tarjeta updateTarjeta(@Valid Tarjeta tarjeta) {
        Tarjeta entity = Tarjeta.findById(tarjeta.id);
        entity.pan = tarjeta.pan;
        entity.saldo = tarjeta.saldo;

        return entity;
    }

    public void deleteTarjeta(Long id) {
        Tarjeta tarjeta = Tarjeta.findById(id);
        tarjeta.delete();
    }
}
// end::adocTransactional[]
