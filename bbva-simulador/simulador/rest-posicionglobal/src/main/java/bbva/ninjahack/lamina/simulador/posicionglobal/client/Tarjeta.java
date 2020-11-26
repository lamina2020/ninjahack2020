// tag::adocBean[]
package bbva.ninjahack.lamina.simulador.posicionglobal.client;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotNull;

@Schema(description="Una tarjeta bancaria")
public class Tarjeta {

    @NotNull
    public int idusuario;
    @NotNull
    public String pan;
    @NotNull
    public int saldo;

    // tag::adocSkip[]
    @Override
    public String toString() {
        return "Tarjeta{" +
            "idUsuario='" + idusuario + '\'' +
            ", pan=" + pan +
            ", saldo='" + saldo + '\'' +
            '}';
    }
    // end::adocSkip[]
}
// end::adocBean[]
