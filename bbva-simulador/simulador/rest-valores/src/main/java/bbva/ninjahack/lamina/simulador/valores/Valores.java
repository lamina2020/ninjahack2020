// tag::adocEntity[]
package bbva.ninjahack.lamina.simulador.valores;

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


@Schema(description = "Una cta. de valores del simulador")
// tag::adocEntity[]
@Entity
public class Valores extends PanacheEntity {

    @NotNull
    public int idusuario;
    @NotNull
    @Size(min = 20, max = 30)
    public String iban;
    @NotNull
    @Size(min = 1, max = 10)
    public int riesgo;
    @NotNull
    @Size(min = 1, max = 2)
    public float interes;
    @NotNull
    public float importe;

    @Override
    public String toString() {
        return "Valores{" +
            "id='" + id +
            ", idusuario='" + idusuario + '\'' +
            ", iban='" + iban + '\'' +
            ", riesgo='" + riesgo + '\'' +
            ", interes='" + interes + '\'' +
        '}';
    }

    public static Valores findByIBAN(String iban){
        return find("iban", iban).firstResult();
    }

    public static List listAll(int idusuario){
        return find("idusuario", idusuario).list();
    }

}
// end::adocEntity[]
