package com.tutorials.ecommerceapp.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryException extends RuntimeException {

    private String resourceName;
    private Object fieldValue;

    public CategoryException(String resourceName, Object fieldValue) {
        super(String.format("%s with Category ID: %s not found", resourceName, fieldValue));
        this.resourceName = resourceName;
        this.fieldValue = fieldValue;
    }

    public CategoryException(String message) {
        super(message);
    }
}


