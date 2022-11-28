package com.tutorials.ecommerceapp.controller;

import com.tutorials.ecommerceapp.dto.cart.AddToCartDto;
import com.tutorials.ecommerceapp.dto.cart.UpdateCart;
import com.tutorials.ecommerceapp.dto.cart.ViewCartResponse;
import com.tutorials.ecommerceapp.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<ViewCartResponse> viewCart(){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.viewCart());
    }

    @DeleteMapping("/removeItemFromCart/{id}")
    public ResponseEntity<String> removeItemFromCart(@PathVariable("id") Long cartId){
        cartService.removeItemFromCart(cartId);
        return new ResponseEntity<String>("Item successfully removed", HttpStatus.OK);
    }

    @PutMapping("/updateCart")
    public ResponseEntity<String> updateCart(@RequestBody @Valid UpdateCart updateCart){
        cartService.updateCart(updateCart);
        return new ResponseEntity<String>("Item successfully updated", HttpStatus.OK);
    }

    @DeleteMapping("/clearCart")
    public ResponseEntity<String> clearCart(){
        cartService.clearCart();
        return new ResponseEntity<String>("Cart cleared", HttpStatus.OK);
    }
}
