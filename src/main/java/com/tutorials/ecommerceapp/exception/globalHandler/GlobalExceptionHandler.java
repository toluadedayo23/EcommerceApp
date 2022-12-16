package com.tutorials.ecommerceapp.exception.globalHandler;

import com.tutorials.ecommerceapp.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CategoryException.class)
    public ErrorResponse handleCategoryException(Exception e){

        HttpStatus status = HttpStatus.NOT_FOUND;
        CategoryException categoryException = (CategoryException) e;

        log.error("Error occurred when processing Category with CATEGORY NAME: {}", categoryException.getFieldValue());

        return new ErrorResponse(status, categoryException.getMessage());
    }

    @ExceptionHandler(ProductException.class)
    public ErrorResponse handleProductException(Exception e){
        HttpStatus status = HttpStatus.NOT_FOUND;

        ProductException productException = (ProductException) e;

        log.error("Error occurred when processing Product with PRODUCT NAME: {}", productException.getFieldValue());

        return new ErrorResponse(status, productException.getMessage());
    }

    @ExceptionHandler(CartException.class)
    public ErrorResponse handleCartException(Exception e){
        HttpStatus status = HttpStatus.NOT_FOUND;

        CartException productException = (CartException) e;

        log.error("Cart is empty, please add some items to your cart");

        return new ErrorResponse(status, productException.getMessage());
    }

    @ExceptionHandler(WishListException.class)
    public ErrorResponse handleWishListException(Exception e){
        HttpStatus status = HttpStatus.NOT_FOUND;

        WishListException wishListException = (WishListException) e;

        log.error("Wish List with ID: {} not found", wishListException.getResourceField());

        return new ErrorResponse(status, wishListException.getMessage());
    }

    @ExceptionHandler(UserException.class)
    public ErrorResponse handleUserException(Exception e){
        HttpStatus status = HttpStatus.NOT_FOUND;

        UserException userException = (UserException) e;

        log.error("User with the username: {} not found",userException.getFieldObject());

        return new ErrorResponse(status, userException.getMessage());

    }

    @ExceptionHandler(RoleException.class)
    public ErrorResponse handleRoleException(Exception e){
        HttpStatus status = HttpStatus.NOT_FOUND;

        RoleException roleException = (RoleException) e;

        log.error("Role {} not found", roleException.getFieldName());

        return new ErrorResponse(status, roleException.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ErrorResponse handleAuthenticationException(AuthenticationException e) {

        HttpStatus status = HttpStatus.UNAUTHORIZED;

        log.error("Authentication failure, user not authenticated");

        return new ErrorResponse(status, e.getMessage());
    }

    @ExceptionHandler(RefreshTokenException.class)
    public ErrorResponse handleRefreshTokenException(Exception e){
        RefreshTokenException refreshTokenException = (RefreshTokenException) e;
        HttpStatus status = HttpStatus.NOT_FOUND;
        log.error("Invalid Refresh Token");
        return new ErrorResponse(status, refreshTokenException.getMessage());
    }
}
