package com.tutorials.ecommerceapp.controller;

import com.tutorials.ecommerceapp.dto.cart.AddToCartDto;
import com.tutorials.ecommerceapp.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<String> addToCart(@RequestBody AddToCartDto addToCartDto){
        cartService.addToCart(addToCartDto);
        return new ResponseEntity<String>("Product successfully added to cart", HttpStatus.CREATED);
    }

    @GetMapping()
    public void viewCart(){
        cartService.viewCart();
    }

}
