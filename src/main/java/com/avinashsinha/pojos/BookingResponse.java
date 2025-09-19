package com.avinashsinha.pojos;

/*
    GSON: It is a Google library used in Rest Assured to convert Java objects to JSON and JSON to Java objects.
    JSON: It is a plain text in key and value pair to transfer from client to server.
*/

// Tool (Website) : https://www.jsonschema2pojo.org/

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingResponse {

    @SerializedName("bookingid")
    @Expose
    private Integer bookingid;
    @SerializedName("booking")
    @Expose
    private Booking booking;

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}