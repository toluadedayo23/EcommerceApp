package com.tutorials.ecommerceapp.dto.cart;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;

@Data
public class UpdateCart {

    @Range(min =1, message = "cartId cannot be less than 1")
    private Long cartId;

    @Range(min =1, message = "productId cannot be less than 1")
    private Long productId;

    @Range(min =1, message = "Quantity cannot be less than 1")
    private Integer quantity;
}
