package com.jsonServerTest.publicRestApis.read;

import com.jsonServerTest.publicRestApis.BasePublicApiTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetPostTest extends BasePublicApiTest {

    @Test
    public void getPostsShouldSucceed() {
        given()
                .log().uri()
                .when()
                .get("/posts/7")
                .then()
                .statusCode(200)
                .log().body()
        .body("id", equalTo(6));
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
