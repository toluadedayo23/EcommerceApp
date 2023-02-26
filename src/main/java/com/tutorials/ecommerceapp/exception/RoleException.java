package com.tutorials.ecommerceapp.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleException extends RuntimeException {
    private String resourceName;
    private Object fieldName;

    public RoleException(String resourceName, Object fieldName){
        super(String.format("%s: %s not found", resourceName, fieldName));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
    }
    public RoleException(String message){
        super(message);
    }
}
