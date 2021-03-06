package com.booking.api;

import com.booking.dataEntities.Booking;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookingApi extends BaseApi {
    protected static final String BOOKING_ENDPOINT = BASE_ENDPOINT + "booking/";

    public static Response getBookingIds() {
        return given()
                .get(BOOKING_ENDPOINT);
    }

    public static Response getBooking(int id, String mediaType) {
        return given()
                .header("Accept", mediaType)
                .get(BOOKING_ENDPOINT + Integer.toString(id));
    }

    public static Response createBooking(Booking payload) {
        return given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(BOOKING_ENDPOINT);
    }
    
    public static Response updatePartialBooking(int id,String token,Booking payload) {
        return given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .body(payload)
                .when()
                .patch(BOOKING_ENDPOINT + Integer.toString(id));
    }

    public static Response deleteBooking(int id, String token) {
        return given()
                .header("Cookie", "token=" + token)
                .delete(BOOKING_ENDPOINT + Integer.toString(id));
    }
}
