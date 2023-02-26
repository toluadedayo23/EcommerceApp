package com.tutorials.ecommerceapp.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.tutorials.ecommerceapp.dto.checkout.CreateCheckoutDto;
import com.tutorials.ecommerceapp.dto.order.CreateOrderDto;
import com.tutorials.ecommerceapp.dto.order.OrderDto;
import com.tutorials.ecommerceapp.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create-checkout-session")
    public ResponseEntity<String> checkoutList(@RequestBody CreateCheckoutDto createCheckoutDto) throws StripeException {
        Session session = orderService.createCheckoutSession(createCheckoutDto);
        return ResponseEntity.ok().body(session.getId());
    }


    @PostMapping("/create-order")
    public ResponseEntity<String> createOrder(@Valid @RequestBody CreateOrderDto orderDto){
        orderService.createOrder(orderDto);
        return ResponseEntity.ok().body("Order has been successfully created");
    }

    @GetMapping("/get-all-orders/{userId}")
    public ResponseEntity<List<OrderDto>> getLastTenOrders(@PathVariable("userId") Long userId){
        return ResponseEntity.ok().body(orderService.getLastTenOrders(userId));
    }

    @GetMapping("/get-order-by-id/{orderId}/{userId}")
    public ResponseEntity<List<OrderDto>> getOrder(@PathVariable("orderId") Long orderId, @PathVariable("userId") Long userId){
        return ResponseEntity.ok().body(orderService.getOrder(orderId, userId));
    }
}
