package com.tutorials.ecommerceapp.dto.cart;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;


@Data
public class AddToCartDto {

    @Range(min = 1, message = "id has to be at least 1")
    private Long productId;

    @Range(min = 1, message = "quantity has to be at least 1")
    private Integer quantity;
}
