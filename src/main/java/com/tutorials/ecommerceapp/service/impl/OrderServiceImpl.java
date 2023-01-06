package com.tutorials.ecommerceapp.service.impl;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.tutorials.ecommerceapp.dto.checkout.CheckoutItemDto;
import com.tutorials.ecommerceapp.dto.order.OrderDto;
import com.tutorials.ecommerceapp.exception.OrderException;
import com.tutorials.ecommerceapp.exception.UserException;
import com.tutorials.ecommerceapp.model.Cart;
import com.tutorials.ecommerceapp.model.Order;
import com.tutorials.ecommerceapp.model.OrderItem;
import com.tutorials.ecommerceapp.model.User;
import com.tutorials.ecommerceapp.repository.CartRepository;
import com.tutorials.ecommerceapp.repository.OrderItemRepository;
import com.tutorials.ecommerceapp.repository.OrderRepository;
import com.tutorials.ecommerceapp.repository.UserRepository;
import com.tutorials.ecommerceapp.service.CartService;
import com.tutorials.ecommerceapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Value("${BASE_URL}")
    private String baseUrl;

    @Value("${STRIPE_SECRET_KEY}")
    private String apikey;

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final CartService cartService;

    @Override
    public Session createCheckoutSession(List<CheckoutItemDto> checkoutItemDtoList) throws StripeException {

        String successURL = baseUrl + "payment/success";

        String failureURL = baseUrl + "payment/failed";

        Stripe.apiKey = apikey;

        List<SessionCreateParams.LineItem> sessionItemList = new ArrayList<>();

        for (CheckoutItemDto checkoutItemDto : checkoutItemDtoList) {
            sessionItemList.add(createSessionLineItem(checkoutItemDto));
        }

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl(failureURL)
                .setSuccessUrl(successURL)
                .addAllLineItem(sessionItemList)
                .build();

        return Session.create(params);

    }

    @Transactional
    @Override
    public void createOrder(OrderDto orderDto) {
        User user = userRepository.findById(orderDto.getUserId()).orElseThrow(
                () -> new UserException("User", orderDto.getUserId()));

        List<Cart> cartList = cartRepository.findByUser(user);

        if(cartList.isEmpty()){
            throw new OrderException("Can't place order, cart is empty; please add items to cart and checkout to make an order");
        }

        Order placeOrder = new Order();

        Double cumPrice = 0.0;

        for(Cart cartItems : cartList){
            cumPrice += (cartItems.getQuantity() * cartItems.getProduct().getPrice());
        }

        placeOrder.setTotalPrice(cumPrice);
        placeOrder.setCreatedDate(new Date());
        placeOrder.setSessionId(orderDto.getSessionId());
        placeOrder.setUser(user);

        Order createdOrder = orderRepository.save(placeOrder);

        saveOrderItems(cartList, createdOrder);

        cartRepository.deleteAllByUser(user);

    }

    private void saveOrderItems(List<Cart> cartList, Order order){

        for(Cart cartItem: cartList){
            OrderItem  newOrderItems = new OrderItem();
            newOrderItems.setQuantity(cartItem.getQuantity());
            newOrderItems.setPrice(cartItem.getProduct().getPrice());
            newOrderItems.setCreatedDate(new Date());
            newOrderItems.setOrder(order);
            newOrderItems.setProduct(cartItem.getProduct());
            orderItemRepository.save(newOrderItems);
        }
    }


    private SessionCreateParams.LineItem createSessionLineItem(CheckoutItemDto checkoutItemDto) {
        return SessionCreateParams.LineItem.builder()
                .setPriceData(createPriceData(checkoutItemDto))
                .setQuantity(checkoutItemDto.getQuantity())
                .build();
    }

    private SessionCreateParams.LineItem.PriceData createPriceData(CheckoutItemDto checkoutItemDto) {
        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("naira")
                .setUnitAmount(Long.parseLong(String.valueOf(checkoutItemDto.getPrice())))
                .setProductData(
                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(checkoutItemDto.getProductName())
                                .build()
                ).build();
    }

    @Override
    public void getAllOrders(Long userId){

    }

    @Override
    public void getOrder(Long orderId) {

    }


}
