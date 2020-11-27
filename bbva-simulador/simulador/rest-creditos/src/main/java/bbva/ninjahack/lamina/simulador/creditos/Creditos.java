// tag::adocEntity[]
package bbva.ninjahack.lamina.simulador.creditos;

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


@Schema(description = "Una cta. de creditos del simulador")
// tag::adocEntity[]
@Entity
public class Creditos extends PanacheEntity {

    @NotNull
    public int idusuario;
    @NotNull
    @Size(min = 20, max = 30)
    public String iban;
    @NotNull
    @Size(min = 1, max = 10)
    public int importe;
    @NotNull
    public float interes;
    @NotNull
    public int plazo;
    @NotNull
    public String tipo;


    @Override
    public String toString() {
        return "Creditos{" +
            "id='" + id +
            ", idusuario='" + idusuario + '\'' +
            ", iban='" + iban + '\'' +
            ", importe='" + importe + '\'' +
            ", interes='" + interes + '\'' +
            ", plazo='" + plazo + '\'' +
            ", tipo='" + tipo + '\'' +
        '}';
    }

    public static Creditos findByIBAN(String iban){
        return find("iban", iban).firstResult();
    }

    public static List listAll(int idusuario){
        return find("idusuario", idusuario).list();
    }

}
// end::adocEntity[]
