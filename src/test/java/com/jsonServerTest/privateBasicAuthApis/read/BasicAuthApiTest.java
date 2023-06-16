package com.jsonServerTest.privateBasicAuthApis.read;

import com.jsonServerTest.privateBasicAuthApis.BaseBasicAuthApiTest;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BasicAuthApiTest extends BaseBasicAuthApiTest {

    @Test
    public void getUserListShouldSucceed() {
        given()
                .contentType(ContentType.JSON)
                .auth().basic("admin", "12345")
                .log().uri()
                .when()
                .get("/users")
                .then()
                .log().body()
                .statusCode(200)
//                .body("data[0].user_id", equalTo(33))
                .body("success", equalTo(true))
                .body("message", equalTo("success"));
    }

    @Test
    public void getUserListDetailShouldSucceed() {
        String userId = given()
                .contentType(ContentType.JSON)
                .auth().basic("admin", "12345")
                .log().uri()
                .when()
                .get("/users")
                .then()
//                .log().body()
                .statusCode(200)
//                .body("success", equalTo("true"))
//                .body("message", equalTo("success"))
        .extract().jsonPath().getString("data[0].user_id");

        given()
                .contentType(ContentType.JSON)
                .auth().basic("admin", "12345")
                .log().uri()
                .when()
                .get("/user/{userId}", userId)
                .then()
                .log().body()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("data[0].user_id", equalTo(userId))
                .body("message", equalTo("Success"));
    }
}
