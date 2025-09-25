package com.avinashsinha.tests.crud;

import com.avinashsinha.base.BaseTest;
import com.avinashsinha.endpoints.APIConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestBookingDateValidation extends BaseTest {

    @Test(groups = "Regression", priority = 1)
    @Owner("Avniash Sinha")
    @Description("TC#1 : Step 1. Verify the Booking by Date Validation")
    public void testVerifyBookingByDate_GET() {

        requestSpecification.basePath(APIConstants.BASE_PATH);

        requestSpecification.queryParam("checkin","2015-01-01");
        requestSpecification.queryParam("checkout","2025-01-01");

        response = RestAssured.given(requestSpecification)
                .when().get();

        validatableResponse = response.then().log().all().statusCode(200);

    }
}
