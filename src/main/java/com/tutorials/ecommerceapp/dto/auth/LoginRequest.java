package com.tutorials.ecommerceapp.dto.auth;

import com.tutorials.ecommerceapp.validation.Password;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "username cannot be empty")
    @Size(min = 2, max = 10, message = "username must be between 2 and 10 characters")
    private String username;

    @NotBlank(message = "password cannot be empty")
    @Password
    private String password;
}
