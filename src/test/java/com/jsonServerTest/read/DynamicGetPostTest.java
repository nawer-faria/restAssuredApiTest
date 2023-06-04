package com.jsonServerTest.read;

import com.jsonServerTest.BasePublicApiTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DynamicGetPostTest extends BasePublicApiTest {
    @Test
    public void dynamicGetPostShouldSucceed(){
        int postId = given()
                .log().uri()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .log().body()
                .extract().jsonPath().getInt("[0].id");

        given()
                .log().uri() //to print url
                .when()
                .get("/posts/{postId}", postId)
                .then()
                .statusCode(200)
                .log().body()
                .body("id", equalTo(postId));
    }

}
