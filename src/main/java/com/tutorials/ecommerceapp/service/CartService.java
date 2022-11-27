package com.tutorials.ecommerceapp.service;

import com.tutorials.ecommerceapp.dto.cart.AddToCartDto;
import com.tutorials.ecommerceapp.dto.cart.ViewCartResponse;

import java.util.List;

public interface CartService {
    void addToCart(AddToCartDto addToCartDto);

    List<ViewCartResponse> viewCart();
}
