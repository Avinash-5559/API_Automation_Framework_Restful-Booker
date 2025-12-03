package com.avinashsinha.tests.integration;

import com.avinashsinha.base.BaseTest;
import com.avinashsinha.endpoints.APIConstants;
import com.avinashsinha.pojos.Booking;
import com.avinashsinha.pojos.BookingResponse;
import com.avinashsinha.pojos.TokenResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestE2EFlow extends BaseTest {

    /*
        Test E2E Scenario:

        1. Verify Health Check                                          : Check the Health
        2. Create a New Token                                           : Token is generated
        3. Create a New Booking ID                                      : Booking ID is created
        4. Verify that the Booking ID is working                        : GET Request to Booking ID
        5. Verify the Booking by Name                                   : GET Request by Name
        6. Verify the Booking by Checkin/Checkout                       : GET Request by Checkin/Checkout
        7. Full Update the Booking ID (need Booking ID and Token)       : Get the bookingId and token from above request
        8. Partial Update the Booking ID (need Booking ID and Token)    : Get the bookingId and token from above request
        9. Delete the Booking ID (need Booking ID and Token)            : Get the bookingId and token from above request
    */

    @Test(groups = "QA", priority = 1)
    @Owner("Avniash Sinha")
    @Description("TC#1 : Step 1. Verify the Health Check")
    public void testHealthCheck_GET() {

        requestSpecification.basePath(APIConstants.PING_URL);

        response = RestAssured.given(requestSpecification)
                .when()
                .get();

        validatableResponse = response.then().log().all().statusCode(201);

    }

    @Test(groups = "QA", priority = 2)
    @Owner("Avniash Sinha")
    @Description("TC#2 : Step 2. Create the Token")
    public void testCreateToken_POST(ITestContext iTestContext) {

        requestSpecification.basePath(APIConstants.AUTH_URL);

        response = RestAssured.given(requestSpecification)
                .body(payloadManager.createTokenRequestAsString())
                .when().log().all()
                .post();

        validatableResponse = response.then().log().all().statusCode(200);

        TokenResponse tokenResponse = payloadManager.tokenResponseJava(response.asString());

        assertActions.verifyStringKeyNotNull(tokenResponse.getToken());

        iTestContext.setAttribute("token", tokenResponse.getToken());

    }

    @Test(groups = "QA", priority = 3)
    @Owner("Avniash Sinha")
    @Description("TC#3 : Step 3. Create the Booking ID")
    public void testCreateBooking_POST(ITestContext iTestContext) {

        requestSpecification.basePath(APIConstants.BASE_PATH);

        response = RestAssured.given(requestSpecification)
                .body(payloadManager.createBookingRequestAsString())
                .when().log().all()
                .post();

        validatableResponse = response.then().log().all().statusCode(200);

        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());

        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(), "Pramod");
        assertActions.verifyIntegerKeyNotNull(bookingResponse.getBookingid());

        iTestContext.setAttribute("bookingid", bookingResponse.getBookingid());
    }

    @Test(groups = "QA", priority = 4)
    @Owner("Avniash Sinha")
    @Description("TC#4 : Step 4. Verify the Booking by ID")
    public void testVerifyBookingId_GET(ITestContext iTestContext) {

        Integer bookingID = (Integer) iTestContext.getAttribute("bookingid");

        requestSpecification.basePath(APIConstants.BASE_PATH + "/" + bookingID);

        response = RestAssured.given(requestSpecification)
                .when().get();

        validatableResponse = response.then().log().all().statusCode(200);

        Booking booking = payloadManager.bookingJava(response.asString());
        assertThat(booking.getFirstname()).isNotNull().isNotEmpty().isNotBlank().isEqualTo("Pramod");

    }

    @Test(groups = "QA", priority = 5)
    @Owner("Avniash Sinha")
    @Description("TC#5 : Step 5. Verify the Booking by Name")
    public void testVerifyBookingByName_GET() {

        requestSpecification.basePath(APIConstants.BASE_PATH);

        requestSpecification.queryParam("firstname","John");
        requestSpecification.queryParam("lastname","Smith");

        response = RestAssured.given(requestSpecification)
                .when().get();

        validatableResponse = response.then().log().all().statusCode(200);

    }

    @Test(groups = "QA", priority = 6)
    @Owner("Avniash Sinha")
    @Description("TC#6 : Step 6. Verify the Booking by Date Validation")
    public void testVerifyBookingByDate_GET() {

        requestSpecification.basePath(APIConstants.BASE_PATH);

        requestSpecification.queryParam("checkin","2015-01-01");
        requestSpecification.queryParam("checkout","2025-01-01");

        response = RestAssured.given(requestSpecification)
                .when().get();

        validatableResponse = response.then().log().all().statusCode(200);

    }

    @Test(groups = "QA", priority = 7)
    @Owner("Avniash Sinha")
    @Description("TC#7 : Step 7. Full Update the Booking by ID")
    public void testUpdateBookingId_PUT(ITestContext iTestContext) {

        Integer bookingID = (Integer) iTestContext.getAttribute("bookingid");

        String basePathUpdate = (String) iTestContext.getAttribute("token");

        requestSpecification.basePath(APIConstants.BASE_PATH + "/" + bookingID);

        response = RestAssured.given(requestSpecification).cookie("token", basePathUpdate)
                .body(payloadManager.fullUpdateBookingRequestAsString())
                .when().log().all()
                .put();

        validatableResponse = response.then().log().all().statusCode(200);

        Booking booking = payloadManager.bookingJava(response.asString());
        assertThat(booking.getFirstname()).isEqualTo("Lucky");
        assertThat(booking.getAdditionalneeds()).isEqualTo("Dinner");

    }

    @Test(groups = "QA", priority = 8)
    @Owner("Avniash Sinha")
    @Description("TC#8 : Step 8. Partial Update the Booking by ID")
    public void testUpdateBookingId_PATCH(ITestContext iTestContext) {

        Integer bookingID = (Integer) iTestContext.getAttribute("bookingid");

        String basePathUpdate = (String) iTestContext.getAttribute("token");

        requestSpecification.basePath(APIConstants.BASE_PATH + "/" + bookingID);

        response = RestAssured.given(requestSpecification).cookie("token", basePathUpdate)
                .body(payloadManager.partialUpdateBookingRequestAsString())
                .when().log().all()
                .patch();

        validatableResponse = response.then().log().all().statusCode(200);

        Booking booking = payloadManager.bookingJava(response.asString());
        assertThat(booking.getDepositpaid()).isEqualTo(true);
        assertThat(booking.getAdditionalneeds()).isEqualTo("Breakfast").isNotBlank().isNotEmpty();

    }

    @Test(groups = "QA", priority = 9)
    @Owner("Avniash Sinha")
    @Description("TC#9 : Step 9. Delete the Booking by ID")
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