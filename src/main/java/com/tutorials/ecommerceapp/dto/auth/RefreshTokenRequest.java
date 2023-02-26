package com.tutorials.ecommerceapp.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class RefreshTokenRequest {

    @NotBlank(message = "Refresh Token cannot be empty")
    private String refreshToken;

    @NotBlank(message = "Refresh Token cannot be empty")
    private  String username;
}
