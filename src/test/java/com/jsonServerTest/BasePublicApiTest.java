package com.jsonServerTest;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BasePublicApiTest {

    @BeforeClass
    public void setup(){
        RestAssured.baseURI="http://localhost";
        RestAssured.port=3000;
        RestAssured.basePath="";
        // if validations fails then will show logging
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
