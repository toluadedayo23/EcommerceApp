package com.tutorials.ecommerceapp.service;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.tutorials.ecommerceapp.dto.checkout.CreateCheckoutDto;
import com.tutorials.ecommerceapp.dto.order.CreateOrderDto;
import com.tutorials.ecommerceapp.dto.order.OrderDto;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;
import java.util.List;

public interface OrderService {

    Session createCheckoutSession(CreateCheckoutDto createCheckoutDto) throws StripeException;

    void createOrder(CreateOrderDto orderDto);

    List<OrderDto> getLastTenOrders(Long userId);

    List<OrderDto> getOrder(Long orderId, Long userId);
}
