package com.jsonServerTest.read;

import com.jsonServerTest.BasePublicApiTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class GetCommentsTest extends BasePublicApiTest {

    @Test(timeOut = 2000)
    public void  getCommentsShouldSucceed(){
        given()
                .log().uri()
                .when()
                .get("/comments")
                .then()
                .statusCode(200)
                .log().body();
//                .body("id", equalTo(1))
//                .body("body", equalTo("some comment"));
    }

    @Test
    public void  getCommentShouldSucceed(){
        given()
                .log().uri()
                .when()
                .get("/comments/1")
                .then()
                .statusCode(200)
                .log().body()
                .body("id", notNullValue());
    }
}
