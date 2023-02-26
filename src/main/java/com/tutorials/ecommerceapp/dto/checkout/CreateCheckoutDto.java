package com.tutorials.ecommerceapp.dto.checkout;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateCheckoutDto {

    @NotEmpty(message = "User Id cannot be empty")
    private long userId;
}
