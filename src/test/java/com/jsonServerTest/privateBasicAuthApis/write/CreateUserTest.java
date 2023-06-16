package com.jsonServerTest.privateBasicAuthApis.write;

import com.jsonServerTest.privateBasicAuthApis.BaseBasicAuthApiTest;
import com.thedeanda.lorem.LoremIpsum;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateUserTest extends BaseBasicAuthApiTest {

    @Test
    public void CreateUserShouldSucceed() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", LoremIpsum.getInstance().getEmail());
        jsonObject.put("name", LoremIpsum.getInstance().getName());
        jsonObject.put("age", 33);
        jsonObject.put("image", LoremIpsum.getInstance().getUrl());
        given()
                .contentType(ContentType.JSON)
                .auth().basic("admin", "12345")
                .log().uri()
                .body(jsonObject)
                .when()
                .post("/user/create")
                .then()
                .log().body()
                .statusCode(200)

                .body("success", equalTo(true))
                .body("message", equalTo("Not Found"))
                .body("status", equalTo(200));
    }

    @Test
    public void updateUserShouldSucceed() {

        String userId = given()
                .contentType(ContentType.JSON)
                .auth().basic("admin", "12345")
                .log().uri()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("data[0].user_id");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", LoremIpsum.getInstance().getEmail());
        jsonObject.put("name", LoremIpsum.getInstance().getName());
        jsonObject.put("age", 33);
        jsonObject.put("image", LoremIpsum.getInstance().getUrl());

        given()
                .contentType(ContentType.JSON)
                .auth().basic("admin", "12345")
                .log().uri()
                .body(jsonObject)
                .when()
                .put("user/edit/{userId}", userId)
                .then()
                .log().body()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo("Success"))
                .body("status", equalTo(200));
    }

    @Test
    public void deleteUserShouldSucceed() {

        String userId = given()
                .contentType(ContentType.JSON)
                .auth().basic("admin", "12345")
                .log().uri()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("data[0].user_id");


        given()
                .contentType(ContentType.JSON)
                .auth().basic("admin", "12345")
                .log().uri()
                .when()
                .put("/user/delete/{userId}", userId)
                .then()
                .statusCode(200)
                .log().body();
    }


}
