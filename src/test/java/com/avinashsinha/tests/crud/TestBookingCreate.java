package com.avinashsinha.tests.crud;

import com.avinashsinha.base.BaseTest;
import com.avinashsinha.endpoints.APIConstants;
import com.avinashsinha.pojos.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class TestBookingCreate extends BaseTest {

    /*
        Create the Booking
        Verify the Booking
        Update the Booking
        Delete the Booking
    */

    @Test(groups = "Regression", priority = 1)
    @Owner("Avniash Sinha")
    @Description("TC#1 : Step 1. Create the Booking ID")
    public void testCreateBooking_POST(ITestContext iTestContext) {

        // Setup will First and Making the Request (Part - 1)

        requestSpecification.basePath(APIConstants.BASE_PATH);

        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingAsString())
                .post();

        // Extraction (Part - 2)

        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());

        // Validation and Verification via the TestNG, AssertJ (Part - 3)

        validatableResponse = response.then().log().all().statusCode(200);
        //assertActions.verifyStatusCode(response, 200);

        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(), "Pramod");
        assertActions.verifyIntegerKeyNotNull(bookingResponse.getBookingid());

        iTestContext.setAttribute("bookingid", bookingResponse.getBookingid());
    }

}