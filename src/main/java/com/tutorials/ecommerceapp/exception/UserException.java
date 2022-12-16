package com.tutorials.ecommerceapp.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserException extends RuntimeException{
    private String resourceName;
    private Object fieldObject;

    public UserException(String resourceName, Object fieldObject){
        super(String.format("%s with the username: %s not found", resourceName, fieldObject));
        this.resourceName = resourceName;
        this.fieldObject = fieldObject;
    }

    public UserException(String message){
        super(message);
    }
}
