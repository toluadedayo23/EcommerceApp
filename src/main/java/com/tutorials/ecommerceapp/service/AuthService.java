package com.tutorials.ecommerceapp.service;

import com.tutorials.ecommerceapp.dto.auth.AuthenticationResponse;
import com.tutorials.ecommerceapp.dto.auth.LoginRequest;
import com.tutorials.ecommerceapp.dto.auth.RefreshTokenRequest;
import com.tutorials.ecommerceapp.dto.auth.SignupRequest;
import com.tutorials.ecommerceapp.model.User;

public interface AuthService {

    void signUp(SignupRequest signupRequest);
    AuthenticationResponse login(LoginRequest loginRequest);
    User getCurrentUser();
    AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
