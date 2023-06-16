package com.jsonServerTest.publicRestApis.write;

import com.jsonServerTest.publicRestApis.BasePublicApiTest;
import com.thedeanda.lorem.LoremIpsum;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class StaticCommentsTest extends BasePublicApiTest {
    @Test(timeOut = 2000)
    public void  createCommentsShouldSucceed(){
        String body = LoremIpsum.getInstance().getTitle(2);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("postId", 4);
        jsonObject.put("body", body);

        given()
                .header("Content-Type", "application/json")
                .body(jsonObject)
                .log().uri()
                .log().body()
                .when()
                .post("/comments")
                .then()
                .statusCode(201)
                .log().body();
    }

    @Test(timeOut = 2000)
    public void  replaceCommentShouldSucceed(){
        String body = LoremIpsum.getInstance().getTitle(2);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("postId", 4);
        jsonObject.put("body", body);

        given()
                .header("Content-Type", "application/json")
                .body(jsonObject)
                .log().uri()
                .log().body()
                .when()
                .put("/comments/4")
                .then()
                .statusCode(200)
                .log().body()
                .body("body", equalTo(body))
                .body("postId", equalTo(4))
                .body("id", notNullValue());
    }

    @Test(timeOut = 2000)
    public void  updateCommentShouldSucceed(){
        String body = LoremIpsum.getInstance().getTitle(2);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("postId", 4);
        jsonObject.put("body", body);

        given()
                .header("Content-Type", "application/json")
                .body(jsonObject)
                .log().uri()
                .log().body()
                .when()
                .patch("/comments/4")
                .then()
                .statusCode(200)
                .log().body()
                .body("body", equalTo(body))
                .body("postId", equalTo(4))
                .body("id", notNullValue());
    }

    @Test(timeOut = 2000)
    public void  deleteCommentShouldSucceed(){
        given()
                .log().uri()
                .log().body()
                .when()
                .delete("/comments/4")
                .then()
                .statusCode(200)
                .log().body();
    }
}
