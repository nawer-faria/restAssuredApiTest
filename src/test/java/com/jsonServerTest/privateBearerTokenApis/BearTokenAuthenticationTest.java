package com.jsonServerTest.privateBearerTokenApis;

import com.thedeanda.lorem.LoremIpsum;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BearTokenAuthenticationTest extends BaseBearerTokenAuthenticationTest {

    @Test
    public void getArticlesShouldSucceed() {
        given()
//               .contentType(ContentType.JSON)
//               .header("Authorization", "Bearer " + getBearerToken())
                .spec(requestSpecification())
                .log().uri()
                .when()
                .get("/articles")
                .then()
                .log().body()
                .statusCode(200);
    }

    @Test
    public void getArticleShouldSucceed() {
        int articleId = given()
                .spec(requestSpecification())
                .log().uri()
                .when()
                .get("/articles")
                .then()
                .statusCode(200)
                .extract().jsonPath().getInt("result[0].id");

        given()
                .spec(requestSpecification())
                .log().uri()
                .when()
                .get("/article/{articleId}", articleId)
                .then()
                .log().body()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo("Login success"))
                .body("id", equalTo(articleId));
    }


    @Test
    public void createArticleShouldSucceed() {
        JSONObject jsonObject = new JSONObject();
        String title = LoremIpsum.getInstance().getTitle(1);
        jsonObject.put("title", title);
        jsonObject.put("body", LoremIpsum.getInstance().getParagraphs(1, 1));
        jsonObject.put("picture", LoremIpsum.getInstance().getUrl());

        given()
                .spec(requestSpecification())
                .body(jsonObject)
                .log().uri()
                .log().body()
                .when()
                .post("/article/create")
                .then()
                .log().body()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo("Article has been created"))
                .body("status", equalTo(200))
                .body("title", equalTo(title));
    }

    @Test
    public void updateArticleShouldSucceed() {
        int articleId = given()
                .spec(requestSpecification())
                .log().uri()
                .when()
                .get("/articles")
                .then()
                .statusCode(200)
                .extract().jsonPath().getInt("result[0].id");

        JSONObject jsonObject = new JSONObject();
        String title = LoremIpsum.getInstance().getTitle(1);
        jsonObject.put("title", title);
        jsonObject.put("body", LoremIpsum.getInstance().getParagraphs(1, 1));

        given()
                .spec(requestSpecification())
                .body(jsonObject)
                .log().uri()
                .log().body()
                .when()
                .put("/article/edit/{articleId}", articleId)
                .then()
                .log().body()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo("Article has been created"))
                .body("status", equalTo(200))
                .body("title", equalTo(title));
    }

    @Test
    public void deleteArticleShouldSucceed() {
        int articleId = given()
                .spec(requestSpecification())
                .log().uri()
                .when()
                .get("/articles")
                .then()
                .statusCode(200)
                .extract().jsonPath().getInt("result[0].id");


        given()
                .spec(requestSpecification())
                .log().uri()
                .when()
                .delete("/article/delete/{articleId}", articleId)
                .then()
                .log().body()
                .statusCode(200);
    }
}
