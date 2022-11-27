package com.tutorials.ecommerceapp.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductException extends RuntimeException {
    private String resourceName;
    private Object fieldValue;

    public ProductException(String resourceName, Object fieldValue){
        super(String.format("%s with the PRODUCT NAME: %s not found", resourceName, fieldValue));
        this.resourceName = resourceName;
        this.fieldValue = fieldValue;
    }

    public ProductException(String message){
        super(message);
    }
}
