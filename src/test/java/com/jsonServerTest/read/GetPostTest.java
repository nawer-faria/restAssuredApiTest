package com.jsonServerTest.read;

import com.jsonServerTest.BasePublicApiTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetPostTest extends BasePublicApiTest {

    @Test(timeOut = 2000)
    public void getPostsShouldSucceed() {
        given()
                .log().uri() //to print url
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .log().body();   // to print response jon
//                .body("id", equalTo(1))
//                .body("title", equalTo("json-server"));
    }

    @Test(timeOut = 2000)
    public void getPostShouldSucceed() {
        given()
                .log().uri() //to print url
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .log().body();   // to print response jon

    }
}
