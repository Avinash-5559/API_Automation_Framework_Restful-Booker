package com.avinashsinha.tests.crud;

import com.avinashsinha.base.BaseTest;
import com.avinashsinha.endpoints.APIConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class TestBookingDeletion extends BaseTest {

    @Test(groups = "Regression", priority = 1)
    @Owner("Avniash Sinha")
    @Description("TC#1 : Step 1. Delete the Booking by ID")
    public void testDeleteBookingId_DELETE(ITestContext iTestContext) {

        Integer bookingID = (Integer) iTestContext.getAttribute("bookingid");

        String basePathUpdate = (String) iTestContext.getAttribute("token");

        requestSpecification.basePath(APIConstants.BASE_PATH + "/" + bookingID);

        response = RestAssured.given(requestSpecification).cookie("token", basePathUpdate)
                .when()
                .delete();

        validatableResponse = response.then().log().all().statusCode(201);
    }
}
