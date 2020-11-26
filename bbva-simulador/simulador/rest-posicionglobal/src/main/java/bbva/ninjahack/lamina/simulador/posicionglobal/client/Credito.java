// tag::adocBean[]
package bbva.ninjahack.lamina.simulador.posicionglobal.client;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotNull;

@Schema(description="Credito bancario")
public class Valor {

    @NotNull
    public int idusuario;
    @NotNull
    public String iban;
    @NotNull
    public int importe;

    // tag::adocSkip[]
    @Override
    public String toString() {
        return "Credito{" +
            "idUsuario='" + idusuario + '\'' +
            ", iban=" + iban +
            ", importe='" + importe + '\'' +
            '}';
    }
    // end::adocSkip[]
}
// end::adocBean[]
