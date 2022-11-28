package com.tutorials.ecommerceapp.service;

import com.tutorials.ecommerceapp.dto.cart.AddToCartDto;
import com.tutorials.ecommerceapp.dto.cart.UpdateCart;
import com.tutorials.ecommerceapp.dto.cart.ViewCartResponse;

import java.util.List;

public interface CartService {
    void addToCart(AddToCartDto addToCartDto);

    ViewCartResponse viewCart();

    void removeItemFromCart(Long cartId);

    void updateCart(UpdateCart updateCart);

    void clearCart();
}
