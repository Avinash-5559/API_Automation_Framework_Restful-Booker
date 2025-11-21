package com.avinashsinha.tests.crud;

import com.avinashsinha.base.BaseTest;
import com.avinashsinha.endpoints.APIConstants;
import com.avinashsinha.pojos.Booking;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestBookingFullUpdate extends BaseTest {

    @Test(groups = "Regression", priority = 1)
    @Owner("Avniash Sinha")
    @Description("TC#1 : Step 1. Full Update the Booking by ID")
    public void testUpdateBookingId_PUT(ITestContext iTestContext) {

        Integer bookingID = (Integer) iTestContext.getAttribute("bookingid");

        String basePathUpdate = (String) iTestContext.getAttribute("token");

        requestSpecification.basePath(APIConstants.BASE_PATH + "/" + bookingID);

        response = RestAssured.given(requestSpecification).cookie("token", basePathUpdate)
                .body(payloadManager.fullUpdatePayloadBookingAsString())
                .when().log().all()
                .put();

        validatableResponse = response.then().log().all().statusCode(200);

        Booking booking = payloadManager.bookingJava(response.asString());
        assertThat(booking.getFirstname()).isEqualTo("Lucky");
        assertThat(booking.getAdditionalneeds()).isEqualTo("Dinner");

    }
}
