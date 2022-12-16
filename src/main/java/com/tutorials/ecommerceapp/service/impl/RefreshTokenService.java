package com.tutorials.ecommerceapp.service.impl;

import com.tutorials.ecommerceapp.dto.RefreshTokenRequest;
import com.tutorials.ecommerceapp.exception.RefreshTokenException;
import com.tutorials.ecommerceapp.model.RefreshToken;
import com.tutorials.ecommerceapp.repository.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public RefreshToken generateRefreshToken(){
        RefreshToken refreshToken = new RefreshToken();
        return refreshTokenRepository.save(refreshToken);
    }

    @Transactional(readOnly = true)
    public void validateRefreshToken(RefreshTokenRequest refreshTokenRequest){
        refreshTokenRepository.findById(refreshTokenRequest.getRefreshToken()).orElseThrow(
                () ->     new RefreshTokenException("Invalid Refresh Token")
        );
    }

    @Transactional
    public void deleteRefreshToken(String token){
        if(refreshTokenRepository.existsById(token)){
            refreshTokenRepository.deleteById(token);
        }else{
            throw new RefreshTokenException("Invalid Token, unable to delete");
        }

    }


}

