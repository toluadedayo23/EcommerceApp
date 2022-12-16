package com.tutorials.ecommerceapp.repository;

import com.tutorials.ecommerceapp.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
}
