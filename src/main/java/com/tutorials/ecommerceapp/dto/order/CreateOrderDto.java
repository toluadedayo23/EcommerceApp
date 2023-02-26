package com.tutorials.ecommerceapp.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDto {
    private Long userId;
    private String sessionId;
}
