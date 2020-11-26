// tag::adocBean[]
package bbva.ninjahack.lamina.simulador.posicionglobal.client;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotNull;

@Schema(description="Valores de Bolsa")
public class Valor {

    @NotNull
    public int idusuario;
    @NotNull
    public String iban;
    @NotNull
    public int riesgo;
    @NotNull
    public int interes;

    // tag::adocSkip[]
    @Override
    public String toString() {
        return "Valor{" +
            "idUsuario='" + idusuario + '\'' +
            ", iban=" + iban +
            ", riesgo='" + riesgo + '\'' +
            ", interes='" + interes + '\'' +
            '}';
    }
    // end::adocSkip[]
}
// end::adocBean[]
