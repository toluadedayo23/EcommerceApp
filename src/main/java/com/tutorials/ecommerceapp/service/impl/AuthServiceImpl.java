package com.tutorials.ecommerceapp.service.impl;

import com.tutorials.ecommerceapp.dto.auth.AuthenticationResponse;
import com.tutorials.ecommerceapp.dto.auth.LoginRequest;
import com.tutorials.ecommerceapp.dto.auth.RefreshTokenRequest;
import com.tutorials.ecommerceapp.dto.auth.SignupRequest;
import com.tutorials.ecommerceapp.exception.RoleException;
import com.tutorials.ecommerceapp.exception.UserException;
import com.tutorials.ecommerceapp.model.ERole;
import com.tutorials.ecommerceapp.model.Role;
import com.tutorials.ecommerceapp.model.User;
import com.tutorials.ecommerceapp.repository.RoleRepository;
import com.tutorials.ecommerceapp.repository.UserRepository;
import com.tutorials.ecommerceapp.security.JwtUtils;
import com.tutorials.ecommerceapp.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final JwtUtils jwtUtils;
    private final RefreshTokenService refreshTokenService;

    @Transactional
    @Override
    public void signUp(SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            throw new UserException("username already exists, please make a new one");
        }
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new UserException("Email already exists, please make a new one");
        }

        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getFirstName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        Set<ERole> signupRole = signupRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (signupRole == null) {
            Role role = roleRepository.findByRole(ERole.ROLE_USER)
                    .orElseThrow(() -> new RoleException("Role", ERole.ROLE_USER));
            roles.add(role);
        }

        if (signupRole != null) {
            signupRole.forEach(role -> {
                switch (role) {
                    case ROLE_ADMIN:
                        Role roleAdmin = roleRepository.findByRole(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RoleException("Role", ERole.ROLE_ADMIN));
                        roles.add(roleAdmin);
                        break;

                    case ROLE_USER:
                        Role roleUser = roleRepository.findByRole(ERole.ROLE_USER)
                                .orElseThrow(() -> new RoleException("Role", ERole.ROLE_USER));
                        roles.add(roleUser);

                        break;
                }
            });
        }
        user.setRole(roles);
        userRepository.save(user);
    }

    @Override
    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateJwtToken(authentication);
        return new AuthenticationResponse(
                token,
                refreshTokenService.generateRefreshToken().getToken(),
                Instant.now().plusMillis(jwtUtils.getJwtExpirationInMillis()),
                loginRequest.getUsername()
        );
    }

    @Override
    @Transactional
    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest);
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtUtils.generateTokenWithUsername(refreshTokenRequest.getUsername());
        return new AuthenticationResponse(
                token,
                refreshTokenService.generateRefreshToken().getToken(),
                Instant.now().plusMillis(jwtUtils.getJwtExpirationInMillis()),
                refreshTokenRequest.getUsername()
        );
    }


    @Override
    public User getCurrentUser() {

        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        User user = new User(null, principal.getUsername(), null, null, null, null, null);

        return userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new UserException("User", user.getUsername()));
    }
}
