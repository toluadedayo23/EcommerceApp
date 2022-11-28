package com.tutorials.ecommerceapp.service.impl;

import com.tutorials.ecommerceapp.dto.cart.AddToCartDto;
import com.tutorials.ecommerceapp.dto.cart.CartItems;
import com.tutorials.ecommerceapp.dto.cart.UpdateCart;
import com.tutorials.ecommerceapp.dto.cart.ViewCartResponse;
import com.tutorials.ecommerceapp.exception.CartException;
import com.tutorials.ecommerceapp.exception.ProductException;
import com.tutorials.ecommerceapp.mapper.CartMapper;
import com.tutorials.ecommerceapp.model.Cart;
import com.tutorials.ecommerceapp.model.Product;
import com.tutorials.ecommerceapp.model.User;
import com.tutorials.ecommerceapp.repository.CartRepository;
import com.tutorials.ecommerceapp.repository.ProductRepository;
import com.tutorials.ecommerceapp.service.AuthService;
import com.tutorials.ecommerceapp.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    private final AuthService authService;

    private final CartMapper cartMapper;

    @Transactional
    @Override
    public void addToCart(AddToCartDto addToCartDto) {
        Product product = productRepository.findById(addToCartDto.getProductId())
                .orElseThrow(() -> new ProductException("Product with the ProductId: " +
                        "" + addToCartDto.getProductId() + " not found"));
        Cart cart = new Cart();
        cart.setCreatedDate(new Date());
        cart.setProduct(product);
        cart.setUser(authService.getCurrentUser());
        cart.setQuantity(addToCartDto.getQuantity());

        cartRepository.save(cart);
    }

    @Override
    public ViewCartResponse viewCart() {
        List<Cart> carts = cartRepository.findByUser(authService.getCurrentUser());

        if (carts.isEmpty()) {
            throw new CartException("Cart is Empty, please add items to cart");
        }

        List<CartItems> cartItemsList = cartMapper.mapCartToCartItemsList(carts);
        Double totalCost = 0.0;

        for (Cart cart : carts){
            totalCost += cart.getQuantity() * cart.getProduct().getPrice();
        }

        ViewCartResponse response = new ViewCartResponse(cartItemsList, totalCost);
        return response;

    }

    @Transactional
    @Override
    public void removeItemFromCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(
                () -> new CartException("Cart with cardId: " + cartId + " not found, please make sure cart is selected"));

        cartRepository.delete(cart);
    }

    @Transactional
    @Override
    public void updateCart(UpdateCart updateCart) {
        Product product = productRepository.findById(updateCart.getProductId())
                .orElseThrow(() -> new ProductException("Product with the ProductId: " +
                        "" + updateCart.getProductId() + " not found"));

        Cart cart = cartRepository.findById(updateCart.getCartId())
                .orElseThrow(() -> new CartException("Cart not found, cannot update, please make sure the right one is selected"));

        cart.setQuantity(updateCart.getQuantity());
        cart.setCreatedDate(new Date());
        cartRepository.save(cart);

    }

    @Transactional
    @Override
    public void clearCart() {
        User user = authService.getCurrentUser();
        cartRepository.deleteAllByUser(user);
    }


}
