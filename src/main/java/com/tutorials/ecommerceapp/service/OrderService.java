package com.tutorials.ecommerceapp.service;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.tutorials.ecommerceapp.dto.checkout.CheckoutItemDto;

import java.util.List;

public interface OrderService {

    Session createSession(List<CheckoutItemDto> checkoutItemDtoList) throws StripeException;
}
