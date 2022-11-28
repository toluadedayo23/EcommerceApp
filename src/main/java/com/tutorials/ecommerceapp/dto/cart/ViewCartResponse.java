package com.tutorials.ecommerceapp.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ViewCartResponse {

    private List<CartItems> cartItems;

    private double totalCost;
}
