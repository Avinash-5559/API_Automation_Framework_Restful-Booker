package com.avinashsinha.tests.crud;

import com.avinashsinha.base.BaseTest;
import com.avinashsinha.endpoints.APIConstants;
import com.avinashsinha.pojos.TokenResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class TestTokenCreate extends BaseTest {

    @Test(groups = "Regression", priority = 1)
    @Owner("Avniash Sinha")
    @Description("TC#1 : Step 1. Create the Token")
    public void testCreateToken_POST(ITestContext iTestContext) {

        // Setup will First and Making the Request (Part - 1)

        requestSpecification.basePath(APIConstants.AUTH_URL);

        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadTokenAsString())
                .post();

        // Extraction (Part - 2)

        TokenResponse tokenResponse = payloadManager.tokenResponseJava(response.asString());

        // Validation and Verification via the TestNG, AssertJ (Part - 3)

        validatableResponse = response.then().log().all().statusCode(200);
        //assertActions.verifyStatusCode(response, 200);

        assertActions.verifyStringKeyNotNull(tokenResponse.getToken());

        iTestContext.setAttribute("token", tokenResponse.getToken());

    }

}