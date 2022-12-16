package com.tutorials.ecommerceapp.service;

import com.tutorials.ecommerceapp.dto.AuthenticationResponse;
import com.tutorials.ecommerceapp.dto.LoginRequest;
import com.tutorials.ecommerceapp.dto.RefreshTokenRequest;
import com.tutorials.ecommerceapp.dto.SignupRequest;
import com.tutorials.ecommerceapp.model.User;

public interface AuthService {

    void signUp(SignupRequest signupRequest);
    AuthenticationResponse login(LoginRequest loginRequest);
    User getCurrentUser();
    AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
