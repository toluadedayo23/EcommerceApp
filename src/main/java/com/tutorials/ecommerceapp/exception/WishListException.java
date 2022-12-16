package com.tutorials.ecommerceapp.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WishListException extends RuntimeException{

    private String name;

    private Object resourceField;

    public WishListException(String name, Object resourceField){
        super(String.format("%s with ID: %s not found", name, resourceField));
        this.name = name;
        this.resourceField = resourceField;
    }

    public WishListException(String message){
        super(message);
    }
}
