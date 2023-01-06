package com.tutorials.ecommerceapp.service;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.tutorials.ecommerceapp.dto.checkout.CheckoutItemDto;
import com.tutorials.ecommerceapp.dto.order.OrderDto;

import java.util.List;

public interface OrderService {

    Session createCheckoutSession(List<CheckoutItemDto> checkoutItemDtoList) throws StripeException;

    void createOrder(OrderDto orderDto);

    void getAllOrders(Long userId);

    void getOrder(Long orderId);
}
