package com.telmopanacas.bookedapi.Security.Auth;

public class AuthenticationResponse {
    private Integer userId;
    private String token;

    public AuthenticationResponse(Integer userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
