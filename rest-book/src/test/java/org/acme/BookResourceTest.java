package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static jakarta.ws.rs.core.HttpHeaders.ACCEPT;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.MediaType.TEXT_PLAIN;
import static jakarta.ws.rs.core.Response.Status.OK;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class BookResourceTest {

    @Test
    public void testGetAllBooks() {
        given()
                .header(ACCEPT, APPLICATION_JSON).
        when()
                .get("/api/books").
        then()
                .statusCode(OK.getStatusCode())
                .body("size()", is(4));
    }

    @Test
    public void testCountBooks(){
        given()
                .header(ACCEPT, TEXT_PLAIN).
        when()
                .get("/api/books/count").
        then()
                .statusCode(OK.getStatusCode())
                .body(is("4"));
    }

    @Test
    public void testGetABook() {
        given()
                .header(ACCEPT, APPLICATION_JSON)
                .pathParam("id", 1).
        when()
                .get("/api/books/{id}").
        then()
                .statusCode(OK.getStatusCode())
                .body("title", is("Understanding Quarkus"))
                .body("author", is("Yoiner"))
                .body("yearOfPublication", is(2024))
                .body("genre", is("IT"));
    }

}