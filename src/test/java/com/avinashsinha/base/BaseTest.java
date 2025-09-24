package com.avinashsinha.base;

// Here, includes Common to all Test Cases

import com.avinashsinha.asserts.AssertActions;
import com.avinashsinha.endpoints.APIConstants;
import com.avinashsinha.modules.PayloadManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    public RequestSpecification requestSpecification;
    public PayloadManager payloadManager;
    public AssertActions assertActions;
    public JsonPath jsonPath;
    public Response response;
    public ValidatableResponse validatableResponse;

    @BeforeMethod
    @BeforeTest
    public void setUp() {

        payloadManager = new PayloadManager();
        assertActions = new AssertActions();

        /*
            --------------- This is also can be used in the Automation Framework ---------------

            requestSpecification = RestAssured.given();
            requestSpecification.baseUri(APIConstants.BASE_URL);
            requestSpecification.contentType(ContentType.JSON).log().all();
        */

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(APIConstants.BASE_URL)
                .addHeader("Content-Type", "application/json")
                .build().log().all();

    }


}
