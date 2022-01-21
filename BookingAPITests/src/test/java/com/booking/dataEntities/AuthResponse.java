package com.booking.dataEntities;

import com.fasterxml.jackson.annotation.JsonProperty;


public class AuthResponse {
    @JsonProperty
    private String token;

    public String getToken() {
        return token;
    }
    
    public AuthResponse() {
    	
    }
}
