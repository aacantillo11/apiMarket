package com.alejo.market.domain.dto;

public class AuthenticationResponse {
    private String Token;

    public AuthenticationResponse(String jwt) {
        this.Token = jwt;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        this.Token = token;
    }
}
