package com.jsonServerTest.publicRestApis.read;

import com.jsonServerTest.publicRestApis.BasePublicApiTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DynamicGetCommentTest extends BasePublicApiTest {

    @Test(timeOut = 2000)
    public void  dynamicGetCommentsShouldSucceed(){
        int commentId = given()
                .log().uri()
                .when()
                .get("/comments")
                .then()
                .statusCode(200)
                .log().body()
                .extract().jsonPath().getInt("[0].id");

        given()
                .log().uri()
                .when()
                .get("/comments/{commentId}", commentId)
                .then()
                .statusCode(200)
                .log().body()
                .body("id", equalTo(commentId));

    }
}
