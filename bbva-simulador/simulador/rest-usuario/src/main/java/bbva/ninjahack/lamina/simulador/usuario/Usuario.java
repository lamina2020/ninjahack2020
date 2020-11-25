// tag::adocEntity[]
package bbva.ninjahack.lamina.simulador.usuario;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;

import java.util.Random;
// end::adocEntity[]
import org.eclipse.microprofile.openapi.annotations.media.Schema;


@Schema(description = "Un usuario del simulador")
// tag::adocEntity[]
@Entity
public class Usuario extends PanacheEntity {

    @NotNull
    @Email
    public String email;
    @NotNull
    @Size(min = 8, max = 50)
    public String password;

    @Override
    public String toString() {
        return "Usuario{" +
            "id=" + id +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
        '}';
    }

    public static Usuario findByEmail(String email){
        return find("email", email).firstResult();
    }

}
// end::adocEntity[]
