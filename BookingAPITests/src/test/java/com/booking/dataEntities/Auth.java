package com.booking.dataEntities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Auth {
    @JsonProperty("username")
    private String userName;

    @JsonProperty("password")
    private String password;
    
    public Auth() {
    }

    public Auth(String name, String pass) {
        userName = name;
        password = pass;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

}
