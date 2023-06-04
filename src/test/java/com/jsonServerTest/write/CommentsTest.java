package com.jsonServerTest.write;

import com.jsonServerTest.BasePublicApiTest;
import com.thedeanda.lorem.LoremIpsum;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CommentsTest extends BasePublicApiTest {
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
}
