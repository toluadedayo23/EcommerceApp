package com.tutorials.ecommerceapp.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
@AllArgsConstructor
public class OrderDto {
    private BigInteger order_id;
    private Date createdDate;
    private String sessionId;
    private Double totalPrice;
    private Double price;
    private Integer quantity;
    private String name;


}
