package com.booking.dataEntities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class BookingDates {
    @JsonProperty
    private Date checkin;

    @JsonProperty
    private Date checkout;
    
    public BookingDates() {
    	this.checkin = null;
        this.checkout = null;
    }

    public BookingDates(Date checkin, Date checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public Date getCheckin() {
        return checkin;
    }

    public Date getCheckout() {
        return checkout;
    }
    
}
