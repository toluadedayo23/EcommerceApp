package com.tutorials.ecommerceapp.service.impl;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.tutorials.ecommerceapp.dto.checkout.CreateCheckoutDto;
import com.tutorials.ecommerceapp.dto.order.CreateOrderDto;
import com.tutorials.ecommerceapp.dto.order.OrderDto;
import com.tutorials.ecommerceapp.exception.OrderException;
import com.tutorials.ecommerceapp.exception.UserException;
import com.tutorials.ecommerceapp.mapper.OrderMapper;
import com.tutorials.ecommerceapp.model.Cart;
import com.tutorials.ecommerceapp.model.Order;
import com.tutorials.ecommerceapp.model.OrderItem;
import com.tutorials.ecommerceapp.model.User;
import com.tutorials.ecommerceapp.repository.CartRepository;
import com.tutorials.ecommerceapp.repository.OrderItemRepository;
import com.tutorials.ecommerceapp.repository.OrderRepository;
import com.tutorials.ecommerceapp.repository.UserRepository;
import com.tutorials.ecommerceapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    private final OrderMapper orderMapper;

    @Override
    public Session createCheckoutSession(CreateCheckoutDto createCheckoutDto) throws StripeException {

        String successURL = baseUrl + "payment/success";

        String failureURL = baseUrl + "payment/failed";

        Stripe.apiKey = apikey;

        User user = userRepository.findById(createCheckoutDto.getUserId()).orElseThrow(() -> new UserException("User with the USERDID: " + createCheckoutDto.getUserId() + " not found"));

        List<Cart> cartList = cartRepository.findByUser(user);

        if (cartList.isEmpty()) {
            throw new OrderException("Cart is empty, cannot create checkout - please add items to cart and retry");
        }

        List<SessionCreateParams.LineItem> sessionItemList = new ArrayList<>();

        for (Cart cartItems : cartList) {
            sessionItemList.add(createSessionLineItem(cartItems));
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
    public void createOrder(CreateOrderDto orderDto) {
        User user = userRepository.findById(orderDto.getUserId()).orElseThrow(
                () -> new UserException("User", orderDto.getUserId()));

        List<Cart> cartList = cartRepository.findByUser(user);

        if (cartList.isEmpty()) {
            throw new OrderException("Can't place order, cart is empty; please add items to cart and checkout to make an order");
        }

        Order placeOrder = new Order();

        Double cumPrice = 0.0;

        for (Cart cartItems : cartList) {
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


    @Override
    public List<OrderDto> getLastTenOrders(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserException("User with the USERID: " + userId + " does not exist"));
        List<Tuple> tupleList = orderRepository.getLastTenOrders(userId);

        return getOrderDto(tupleList);

    }

    @Override
    public List<OrderDto> getOrder(Long orderId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserException("User with the USERID: " + userId + " does not exist"));
        List<Tuple> tupleList = orderRepository.getOrderByIdAndUserId(orderId, userId);

        return getOrderDto(tupleList);
    }


    private void saveOrderItems(List<Cart> cartList, Order order) {

        for (Cart cartItem : cartList) {
            OrderItem newOrderItems = new OrderItem();
            newOrderItems.setQuantity(cartItem.getQuantity());
            newOrderItems.setPrice(cartItem.getProduct().getPrice());
            newOrderItems.setCreatedDate(new Date());
            newOrderItems.setOrder(order);
            newOrderItems.setProduct(cartItem.getProduct());
            orderItemRepository.save(newOrderItems);
        }
    }


    private SessionCreateParams.LineItem createSessionLineItem(Cart cartItems) {
        return SessionCreateParams.LineItem.builder()
                .setPriceData(createPriceData(cartItems))
                .setQuantity(Long.parseLong(Integer.toString(cartItems.getQuantity())))
                .build();
    }

    private SessionCreateParams.LineItem.PriceData createPriceData(Cart cartItems) {
        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("ngn")
                .setUnitAmountDecimal(new BigDecimal(String.valueOf(cartItems.getProduct().getPrice())))
                .setProductData(
                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(cartItems.getProduct().getName())
                                .build()
                ).build();
    }



    private List<OrderDto> getOrderDto(List<Tuple> tupleList) {
        List<OrderDto> dtoList = tupleList.stream()
                .map(tuples ->
                        new OrderDto(
                                tuples.get(0, BigInteger.class),
                                tuples.get(1, Date.class),
                                tuples.get(2, String.class),
                                tuples.get(3, Double.class),
                                tuples.get(4, Double.class),
                                tuples.get(5, Integer.class),
                                tuples.get(6, String.class)
                        )
                ).collect(Collectors.toList());
        return dtoList;
    }


}
