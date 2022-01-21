package com.booking.api;

import static com.booking.util.Global.DEFAULT_PASSWORD;
import static com.booking.util.Global.DEFAULT_USER_NAME;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Date;

import org.apache.http.impl.client.RequestWrapper;
import org.hamcrest.core.IsNot;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import static org.assertj.core.api.Assertions.assertThat;


import com.booking.api.AuthApi;
import com.booking.api.BookingApi;
import com.booking.dataEntities.Auth;
import com.booking.dataEntities.AuthResponse;
import com.booking.dataEntities.Booking;
import com.booking.dataEntities.BookingDates;
import com.booking.dataEntities.BookingResponse;

import io.restassured.RestAssured;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseBodyData;

public class BookingTest {
	static PrintStream printInLogFile;
	
	 @Rule 
	 public TestName name = new TestName();
	
	@BeforeClass
	public static void testSetUp() throws FileNotFoundException {
		printInLogFile = new PrintStream(new File("src//test//resources//log.txt"));
		new ResponseLoggingFilter();
		new RequestLoggingFilter();
		new ErrorLoggingFilter();
		RestAssured.filters(RequestLoggingFilter.logRequestTo(printInLogFile), ResponseLoggingFilter.logResponseTo(printInLogFile),ErrorLoggingFilter.logErrorsTo(printInLogFile));
		printInLogFile.flush();
		
	}
	
	@Before
	public void beforeMethodSetUp() {
		printInLogFile.print("******************************************************************\n"
				+ "             "+name.getMethodName()+" logs           \n"
				+ "*********************************************************************\n");
		
	}

    @Test
    public void TC1_createToken() throws FileNotFoundException {    
        Auth auth = new Auth(DEFAULT_USER_NAME,DEFAULT_PASSWORD);
        Response response = AuthApi.createToken(auth);
        assertThat(response.getStatusCode() == 200).isTrue();
    }

    @Test
    public void TC2_getBookingIds() {
        Response response = BookingApi.getBookingIds();
        assertThat(response.getStatusCode() == 200).isTrue();
        assertThat(response.asString().contains("bookingid"));
       
    }

    @Test
    public void TC3_getBookingById() {
        Response response = BookingApi.getBooking(3, "application/json");
        assertThat(response.getStatusCode() == 200).isTrue();
    }

    @Test
    public void TC4_createBooking() {        
    	BookingDates dates = new BookingDates(new Date(),new Date());
        Booking payload = new Booking("Mary","Active",200,true,dates,"None");
        Response response = BookingApi.createBooking(payload);
        assertThat(response.getStatusCode() == 200).isTrue();
    }
    
    @Test
    public void TC5_partialUpdateBooking() {  
    	BookingDates dates = new BookingDates();
    	Auth auth = new Auth(DEFAULT_USER_NAME,DEFAULT_PASSWORD);
        AuthResponse authResponse = AuthApi.createToken(auth).as(AuthResponse.class);
        Booking payload = new Booking("Kate","Fisher");
        Response response = BookingApi.updatePartialBooking(3, authResponse.getToken(), payload);
        assertThat(response.getStatusCode() == 200).isTrue();
    }

    @Test
    public void TC6_deleteBooking() {
        BookingDates dates = new BookingDates(new Date(),new Date());
        Booking payload = new Booking("Test","Test",100,true,dates,"None");
        BookingResponse bookingResponse = BookingApi.createBooking(payload).as(BookingResponse.class);
        Auth auth = new Auth(DEFAULT_USER_NAME,DEFAULT_PASSWORD);
        AuthResponse authResponse = AuthApi.createToken(auth).as(AuthResponse.class);
        Response response = BookingApi.deleteBooking(bookingResponse.getBookingId(), authResponse.getToken());
        assertThat(response.getStatusCode() == 201).isTrue();
    }
}
