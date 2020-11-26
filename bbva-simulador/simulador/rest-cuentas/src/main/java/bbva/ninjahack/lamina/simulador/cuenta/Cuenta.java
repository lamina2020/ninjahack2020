// tag::adocEntity[]
package bbva.ninjahack.lamina.simulador.cuenta;

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


@Schema(description = "Una cuenta del simulador")
// tag::adocEntity[]
@Entity
public class Cuenta extends PanacheEntity {

    @NotNull
    public int idusuario;
    @NotNull
    @Size(min = 20, max = 30)
    public String iban;
    @NotNull
    @Size(min = 1, max = 10)
    public int saldo;

    @Override
    public String toString() {
        return "Cuenta{" +
            "id='" + id +
            ", idusuario='" + idusuario + '\'' +
            ", iban='" + iban + '\'' +
            ", saldo='" + saldo + '\'' +
        '}';
    }

    public static Cuenta findByIBAN(String iban){
        return find("iban", iban).firstResult();
    }

    public static List listAll(int idusuario){
        return find("idusuario", idusuario).list();
    }

}
// end::adocEntity[]
