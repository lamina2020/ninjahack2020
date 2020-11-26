// tag::adocBean[]
package bbva.ninjahack.lamina.simulador.posicionglobal.client;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotNull;

@Schema(description="Una cuenta bancaria")
public class Cuenta {

    @NotNull
    public int idusuario;
    @NotNull
    public String iban;
    @NotNull
    public int saldo;

    // tag::adocSkip[]
    @Override
    public String toString() {
        return "Cuenta{" +
            "idUsuario='" + idusuario + '\'' +
            ", iban=" + iban +
            ", saldo='" + saldo + '\'' +
            '}';
    }
    // end::adocSkip[]
}
// end::adocBean[]
