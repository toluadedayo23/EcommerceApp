package com.tutorials.ecommerceapp.exception;

public class RefreshTokenException extends RuntimeException {
    public RefreshTokenException(String invalid_refresh_token) {
        super(invalid_refresh_token);
    }
}
