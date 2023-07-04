package com.jsonServerTest.publicRestApis.write;

import com.jsonServerTest.publicRestApis.BasePublicApiTest;
import com.thedeanda.lorem.LoremIpsum;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DynamicCommentsTest extends BasePublicApiTest {
    @Test
    public void dynamicCreateCommentsShouldSucceed() {
        int postId = given()
                .log().uri()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .log().body()
                .extract().jsonPath().getInt("[0].id");

        String body = LoremIpsum.getInstance().getTitle(2);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("postId", postId);
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

    @Test
    public void dynamicReplaceCommentsShouldSucceed() {
        int postId = given()
                .log().uri()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .log().body()
                .extract().jsonPath().getInt("[0].id");

        int commentId = given()
                .log().uri()
                .when()
                .get("/comments")
                .then()
                .statusCode(200)
                .log().body()
                .extract().jsonPath().getInt("[0].id");

        String body = LoremIpsum.getInstance().getTitle(2);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("postId", postId);
        jsonObject.put("body", body);

        given()
                .header("Content-Type", "application/json")
                .body(jsonObject)
                .log().uri()
                .log().body()
                .when()
                .put("/comments/{commentId}", commentId)
                .then()
                .statusCode(200)
                .log().body()
                .body("body", equalTo(body));
    }

    @Test
    public void dynamicUpdateCommentsShouldSucceed() {

        int commentId = given()
                .log().uri()
                .when()
                .get("/comments")
                .then()
                .statusCode(200)
                .log().body()
                .extract().jsonPath().getInt("[0].id");

        String body = LoremIpsum.getInstance().getTitle(2);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("body", body);

        given()
                .header("Content-Type", "application/json")
                .body(jsonObject)
                .log().uri()
                .log().body()
                .when()
                .patch("/comments/{commentId}", commentId)
                .then()
                .statusCode(200)
                .log().body()
                .body("body", equalTo(body));
    }

    @Test
    public void dynamicDeleteCommentsShouldSucceed() {

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
                .log().body()
                .when()
                .patch("/comments/{commentId}", commentId)
                .then()
                .statusCode(200)
                .log().body();
    }
}
