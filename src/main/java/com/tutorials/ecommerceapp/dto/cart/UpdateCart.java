package com.tutorials.ecommerceapp.dto.cart;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UpdateCart {

    @NotEmpty
    private Long cartId;

    @NotEmpty
    private Long productId;

    @NotEmpty
    private Integer quantity;
}
