// tag::adocTransactional[]
package bbva.ninjahack.lamina.simulador.usuario;

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
public class UsuarioService {

    @Transactional(SUPPORTS)
    public List<Usuario> findAllUsuarios() {
        return Usuario.listAll();
    }

    @Transactional(SUPPORTS)
    public Usuario findUsuarioById(Long id) {
        return Usuario.findById(id);
    }

    @Transactional(SUPPORTS)
    public Usuario findUsuarioByEmail(String email) {
        return Usuario.findByEmail(email);
    }

    // tag::adocPersistUsuario[]
    public Usuario persistUsuario(@Valid Usuario usuario) {
        Usuario.persist(usuario);
        return usuario;
    }
    // end::adocPersistUsuario[]

    public Usuario updateUsuario(@Valid Usuario usuario) {
        Usuario entity = Usuario.findById(usuario.id);
        entity.email = usuario.email;
        entity.password = usuario.password;

        return entity;
    }

    public void deleteUsuario(Long id) {
        Usuario usuario = Usuario.findById(id);
        usuario.delete();
    }
}
// end::adocTransactional[]
