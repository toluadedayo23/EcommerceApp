package com.tutorials.ecommerceapp.dto.cart;

import lombok.Data;

import java.util.List;

@Data

public class ViewCartResponse {

    private List<CartItems> cartItems;

    private double totalCost;
}
