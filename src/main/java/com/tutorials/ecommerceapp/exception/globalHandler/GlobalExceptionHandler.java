package com.tutorials.ecommerceapp.exception.globalHandler;

import com.tutorials.ecommerceapp.exception.CartException;
import com.tutorials.ecommerceapp.exception.CategoryException;
import com.tutorials.ecommerceapp.exception.ErrorResponse;
import com.tutorials.ecommerceapp.exception.ProductException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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


}
