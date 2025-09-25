package com.avinashsinha.modules;

// Here, convert Java objects to JSON and vice versa

import com.avinashsinha.pojos.*;
import com.google.gson.Gson;

public class PayloadManager {

    public String createPayloadBookingAsString() {

        Booking booking = new Booking();

        booking.setFirstname("Pramod");
        booking.setLastname("Dutta");
        booking.setTotalprice(123);
        booking.setDepositpaid(true);

        BookingDates bookingDates = new BookingDates();

        bookingDates.setCheckin("2025-10-01");
        bookingDates.setCheckout("2025-10-10");

        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("Lunch");

        // Serialization - Converting Java Objects to JSON by GSON

        Gson gson = new Gson();

        String jsonBookingString = gson.toJson(booking);
        return jsonBookingString;
    }

    // Deserialization - Converting JSON to Java Objects by GSON

    public BookingResponse bookingResponseJava(String bookingResponseString) {

        Gson gson = new Gson();

        BookingResponse bookingResponse = gson.fromJson(bookingResponseString, BookingResponse.class);
        return bookingResponse;
    }

    public String createPayloadTokenAsString() {

        Auth auth = new Auth();

        auth.setUsername("admin");
        auth.setPassword("password123");

        // Serialization - Converting Java Objects to JSON by GSON

        Gson gson = new Gson();

        String jsonTokenString = gson.toJson(auth);
        return jsonTokenString;

    }

    // Deserialization - Converting JSON to Java Objects by GSON

    public TokenResponse tokenResponseJava(String tokenString) {

        Gson gson = new Gson();

        TokenResponse tokenResponse = gson.fromJson(tokenString, TokenResponse.class);
        return tokenResponse;
    }

    public Booking bookingJava(String bookingString) {
        Gson gson = new Gson();

        Booking booking = gson.fromJson(bookingString, Booking.class);
        return booking;
    }

    public String fullUpdatePayloadBookingAsString() {

        Booking booking = new Booking();

        booking.setFirstname("Lucky");
        booking.setLastname("Mathur");
        booking.setTotalprice(456);
        booking.setDepositpaid(false);

        BookingDates bookingDates = new BookingDates();

        bookingDates.setCheckin("2025-11-11");
        bookingDates.setCheckout("2025-11-20");

        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("Dinner");

        Gson gson = new Gson();

        String jsonFullUpdateString = gson.toJson(booking);
        return jsonFullUpdateString;
    }

    public String partialUpdatePayloadBookingAsString() {

        Booking booking = new Booking();

        booking.setTotalprice(147);
        booking.setDepositpaid(true);

        booking.setAdditionalneeds("Breakfast");

        Gson gson = new Gson();

        String jsonPartialUpdateString = gson.toJson(booking);
        return jsonPartialUpdateString;
    }

}