package com.booking.dataEntities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingResponse {
    @JsonProperty("bookingid")
    private int bookingId;

    @JsonProperty
    private Booking booking;

    public int getBookingId() {
        return bookingId;
    }

    public Booking getBooking() {
        return booking;
    }
    
    public BookingResponse() {	
    }
    
    public BookingResponse(int bookingId,Booking booking) {
    	this.bookingId = bookingId;
    	this.booking = booking;
    }
    
}
