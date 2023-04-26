package EU10.Spartan.admin;

import io.restassured.http.*;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit5.*;
import net.serenitybdd.rest.*;
import org.junit.jupiter.api.*;

import java.util.function.Consumer;

import static io.restassured.RestAssured.*;
import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.*;
import static org.hamcrest.Matchers.*;
@Disabled
@SerenityTest
public class SpartanAdminGetTest {
    @BeforeAll
    public static void setUpBase() {
        baseURI = "http://3.216.30.92:7000";
    }

    @Test
    public void getAllSpartan() {
        given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin","admin")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .extract().response();
    }

    @Test
    public void getOneSpartan() {
        given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin","admin")
                .pathParam("id",15)
                .when()
                .get("/api/spartans/{id}");

        System.out.println("statusCode = " + lastResponse().statusCode());
        System.out.println("contentType = " + lastResponse().contentType());
        System.out.println("id = " + lastResponse().path("id"));
        System.out.println("name = " + lastResponse().jsonPath().get("name"));
    }

    @DisplayName("GET request with Serenity Assertion way")
    @Test
    public void getOneSpartanAssertion(){
        given().contentType(ContentType.JSON).
                and().
                auth().basic("admin","admin").
                pathParam("id",15).when().
                get("/api/spartans/{id}");


        Ensure.that("status code is 200",validatableResponse -> validatableResponse.statusCode(200));
        Ensure.that("content type is Json",validatableResponse -> validatableResponse.contentType(ContentType.JSON));
        Ensure.that("content type is Json",vRes -> vRes.contentType(ContentType.JSON));
        Ensure.that("the id is 15",validatableResponse -> validatableResponse.body("id",is(15)));
    }
}
