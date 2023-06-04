package com.jsonServerTest.write;

import com.jsonServerTest.BasePublicApiTest;
import com.thedeanda.lorem.LoremIpsum;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class StaticPostsTest extends BasePublicApiTest {

    @Test
    public void createPostUsingMapShouldSucceed() {

        // Sending request body using HashMap
        String authorName = LoremIpsum.getInstance().getName();
//        Map<Object, Object> json = new HashMap<>();
        Map json = new HashMap<>(); //by default takes object, object

        //       json.put("title", LoremIpsum.getInstance().getTitle(2));
        json.put("title", "API Testing");
        json.put("author", authorName);

        given()
                .header("Content-Type", "application/json")
                .body(json)
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().body()
                .body("author", equalTo(authorName))
                .body("title", equalTo("API Testing"))
                .body("id", notNullValue());
    }

    @Test
    public void createPostUsingJsonShouldSucceed() {

        // Sending request body using Json
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
    public void replacePostUsingJsonShouldSucceed() {
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
                .put("/posts/4")
                .then()
                .statusCode(200)
                .log().body()
                .body("author", equalTo(authorName))
                .body("id", equalTo(4))
                .body("id", notNullValue());
    }

    @Test
    public void updatePostUsingJsonShouldSucceed() {
        String authorName = LoremIpsum.getInstance().getName();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("author", authorName);

        given()
                .header("Content-Type", "application/json")
                .body(jsonObject)
                .log().uri()
                .log().body()
                .when()
                .patch("/posts/5")
                .then()
                .statusCode(200)
                .log().body()
                .body("author", equalTo(authorName))
                .body("id", equalTo(5))
                .body("id", notNullValue());
    }

    @Test
    public void deletePostShouldSucceed() {
        given()
                .log().uri()
                .when()
                .delete("/posts/7")
                .then()
                .statusCode(200)
                .log().body();
    }


}