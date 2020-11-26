// tag::adocEntity[]
package bbva.ninjahack.lamina.simulador.tarjeta;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

import java.util.Random;
// end::adocEntity[]
import org.eclipse.microprofile.openapi.annotations.media.Schema;


@Schema(description = "Una tarjeta del simulador")
// tag::adocEntity[]
@Entity
public class Tarjeta extends PanacheEntity {

    @NotNull
    public int idusuario;
    @NotNull
    @Size(min = 1, max = 30)
    public String pan;
    @NotNull
    @Size(min = 1, max = 10)
    public int saldo;

    @Override
    public String toString() {
        return "Tarjeta{" +
            "id='" + id +
            ", idusuario='" + idusuario + '\'' +
            ", pan='" + pan + '\'' +
            ", saldo='" + saldo + '\'' +
        '}';
    }

    public static Tarjeta findByPAN(String pan){
        return find("pan", pan).firstResult();
    }

    public static List listAll(int idusuario){
        return find("idusuario", idusuario).list();
    }

}
// end::adocEntity[]
