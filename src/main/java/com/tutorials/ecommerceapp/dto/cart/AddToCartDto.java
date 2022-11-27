package com.tutorials.ecommerceapp.dto.cart;

import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class AddToCartDto {

    @NotEmpty
    private Long productId;

    @NotEmpty
    private Integer quantity;
}
