package com.jsonServerTest.write;

import com.jsonServerTest.BasePublicApiTest;
import com.thedeanda.lorem.LoremIpsum;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.prefs.BackingStoreException;

import static io.restassured.RestAssured.given;

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
}
