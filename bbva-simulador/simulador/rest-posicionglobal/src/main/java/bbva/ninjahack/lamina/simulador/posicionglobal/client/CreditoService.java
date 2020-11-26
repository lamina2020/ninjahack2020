// tag::adocRestClient[]
package bbva.ninjahack.lamina.simulador.posicionglobal.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;
import bbva.ninjahack.lamina.simulador.posicionglobal.client.Credito;

@Path("/api/creditos")
@Produces(MediaType.APPLICATION_JSON)
@RegisterRestClient
public interface CreditoService {

    @GET
    @Path("/usuarios/{idUsuario}")
    List<Cuenta> getAllCreditos(int idUsuario);
}
// end::adocRestClient[]
