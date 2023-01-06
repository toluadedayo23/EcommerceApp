package com.tutorials.ecommerceapp.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.tutorials.ecommerceapp.dto.checkout.CheckoutItemDto;
import com.tutorials.ecommerceapp.dto.order.OrderDto;
import com.tutorials.ecommerceapp.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create-checkout-session")
    public ResponseEntity<String> checkoutList(@RequestBody List<CheckoutItemDto> checkoutItemDtoList) throws StripeException {
        Session session = orderService.createCheckoutSession(checkoutItemDtoList);
        return ResponseEntity.ok().body(session.getId());
    }


    @PostMapping("/create-order")
    public ResponseEntity<String> createOrder(@RequestBody OrderDto orderDto){
        orderService.createOrder(orderDto);
        return ResponseEntity.ok().body("Order has been successfully created");
    }
}
