package com.tutorials.ecommerceapp.controller;

import com.tutorials.ecommerceapp.dto.auth.AuthenticationResponse;
import com.tutorials.ecommerceapp.dto.auth.LoginRequest;
import com.tutorials.ecommerceapp.dto.auth.RefreshTokenRequest;
import com.tutorials.ecommerceapp.dto.auth.SignupRequest;
import com.tutorials.ecommerceapp.service.AuthService;
import com.tutorials.ecommerceapp.service.impl.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth/")
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequest signupRequest) {
        authService.signUp(signupRequest);
        return ResponseEntity.ok().body("Registration successful");
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok().body(authService.login(loginRequest));
    }

    @PostMapping("refresh-token")
    public ResponseEntity<AuthenticationResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok().body(authService.refreshToken(refreshTokenRequest));
    }

    @PostMapping("logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.ok().body("User has successfully logged out!!!");
    }
}
