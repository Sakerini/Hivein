package com.hivein.authservice.model.response;

public class TokenInvalidResponse extends ErrorResponse {
    public TokenInvalidResponse(String code, String message) {
        super(code, message);
    }
}
