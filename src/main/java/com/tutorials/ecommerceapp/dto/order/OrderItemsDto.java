package com.tutorials.ecommerceapp.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItemsDto {
    private int quantity;
    private double price;
    private Long orderId;
    private Long productId;
}
