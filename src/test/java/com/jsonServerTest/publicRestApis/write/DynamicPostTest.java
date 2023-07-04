package com.jsonServerTest.publicRestApis.write;

import com.jsonServerTest.publicRestApis.BasePublicApiTest;
import com.thedeanda.lorem.LoremIpsum;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class DynamicPostTest extends BasePublicApiTest {
    @Test
    public void createPostUsingJsonShouldSucceed() {
        String authorName = LoremIpsum.getInstance().getName();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", LoremIpsum.getInstance().getTitle(2));
        jsonObject.put("author", authorName);

        given()
                .header("Content-Type", "application/json")
                .body(jsonObject)
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().body()
                .body("author", equalTo(authorName))
                .body("id", notNullValue());
    }


    @Test
    public void dynamicallyReplacePostUsingJsonShouldSucceed() {
        int postId = given()
                .log().uri() //to print url
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .log().body()
                .extract().jsonPath().getInt("[0].id");

        String authorName = LoremIpsum.getInstance().getName();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", LoremIpsum.getInstance().getTitle(2));
        jsonObject.put("author", authorName);

        given()
                .header("Content-Type", "application/json")
                .body(jsonObject)
                .log().uri()
                .log().body()
                .when()
                .put("/posts/{postId}", postId)
                .then()
                .statusCode(200)
                .log().body()
                .body("author", equalTo(authorName))
                .body("id", equalTo(postId))
                .body("id", notNullValue());
    }

    @Test
    public void dynamicallyUpdatePostUsingJsonShouldSucceed() {
        int postId = given()
                .log().uri() //to print url
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .log().body()
                .extract().jsonPath().getInt("[0].id");

        String authorName = LoremIpsum.getInstance().getName();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("author", authorName);

        given()
                .header("Content-Type", "application/json")
                .body(jsonObject)
                .log().uri()
                .log().body()
                .when()
                .patch("/posts/{postId}", postId)
                .then()
                .statusCode(200)
                .log().body()
                .body("author", equalTo(authorName))
                .body("id", equalTo(postId))
                .body("id", notNullValue());
    }

    @Test
    public void dynamicallyDeletePostUsingJsonShouldSucceed() {
        int postId = given()
                .log().uri() //to print url
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .log().body()
                .extract().jsonPath().getInt("[0].id");

        given()
                .log().uri()
                .log().body()
                .when()
                .delete("/posts/{postId}", postId)
                .then()
                .statusCode(200)
                .log().body();
    }
}
