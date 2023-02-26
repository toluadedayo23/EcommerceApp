package com.tutorials.ecommerceapp.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class OrderItemDto {

    private int quantity;

    private Double price;

    private Date createdDate;

    private String productName;
}
