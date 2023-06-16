package com.jsonServerTest.privateBasicAuthApis;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public class BaseBasicAuthApiTest {

    @BeforeClass
    public void setup(){
        RestAssured.baseURI="https://apingweb.com";
//        RestAssured.port=80;
        RestAssured.basePath="/api/auth";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

}
