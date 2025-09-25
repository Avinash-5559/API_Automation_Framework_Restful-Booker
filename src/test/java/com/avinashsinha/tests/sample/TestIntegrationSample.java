package com.avinashsinha.tests.sample;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestIntegrationSample {

    /*
        Create the Booking
        Verify the Booking
        Update the Booking
        Delete the Booking
    */

    @Test(groups = "qa", priority = 1)
    @Owner("Avniash Sinha")
    @Description("TC#1 : Step 1. Create the Booking ID")
    public void testCreateBooking() {
        Assert.assertTrue(true);
    }

    @Test(groups = "qa", priority = 2)
    @Owner("Avniash Sinha")
    @Description("TC#2 : Step 2. Verify the Booking by ID")
    public void testVerifyBookingId() {
        Assert.assertTrue(true);
    }

    @Test(groups = "qa", priority = 3)
    @Owner("Avniash Sinha")
    @Description("TC#3 : Step 3. Update the Booking by ID")
    public void testUpdateBookingId() {
        Assert.assertTrue(true);
    }

    @Test(groups = "qa", priority = 4)
    @Owner("Avniash Sinha")
    @Description("TC#4 : Step 4. Delete the Booking by ID")
    public void testDeleteBookingId() {
        Assert.assertTrue(true);
    }

}