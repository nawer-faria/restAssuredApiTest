package com.jsonServerTest.privateRestApis;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseBearerTokenAuthenticationTest {

    @BeforeClass
    public void setup(){
        RestAssured.baseURI="https://apingweb.com";
        RestAssured.basePath="/api";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
