// tag::adocResourceTest[]
package bbva.ninjahack.lamina.simulador.usuario;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Random;

import bbva.ninjahack.lamina.simulador.usuario.Usuario;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.post;
import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
//@QuarkusTestResource(DatabaseResource.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioResourceTest{


    // tag::adocOpenAPI[]
    @Test
    void shouldPingOpenAPI() {
        given()
            .header(ACCEPT, APPLICATION_JSON)
            .when().get("/openapi")
            .then()
            .statusCode(OK.getStatusCode());
    }

    @Test
    void shouldPingSwaggerUI() {
        given()
            .when().get("/swagger-ui")
            .then()
            .statusCode(OK.getStatusCode());
    }
    // end::adocOpenAPI[]

    // tag::adocHealth[]
    @Test
    void shouldPingLiveness() {
        given()
            .when().get("/health/live")
            .then()
            .statusCode(OK.getStatusCode());
    }

    @Test
    void shouldPingReadiness() {
        given()
            .when().get("/health/ready")
            .then()
            .statusCode(OK.getStatusCode());
    }
    // end::adocHealth[]

    @Test
    void shouldGetToken(){
        Usuario usuario = new Usuario();
        usuario.email = "user3@lamina.com";
        usuario.password = "openshift";

        given()
            .body(usuario)
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .header(ACCEPT, APPLICATION_JSON)
        .when().post("/api/usuarios/login")
            .then()
            .statusCode(OK.getStatusCode());
    }

}
// end::adocResourceTest[]
