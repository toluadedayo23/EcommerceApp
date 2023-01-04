package com.tutorials.ecommerceapp.dto.checkout;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CheckoutItemDto {

    private String productName;
    private Long quantity;
    private Double price;
    private Long productId;
}
