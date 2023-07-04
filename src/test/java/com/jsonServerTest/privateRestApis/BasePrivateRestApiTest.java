package com.jsonServerTest.privateRestApis;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BasePrivateRestApiTest {

    @BeforeClass
    public void setup(){
        RestAssured.baseURI="https://apingweb.com";
//        RestAssured.port=80;
        RestAssured.basePath="/api/auth";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
