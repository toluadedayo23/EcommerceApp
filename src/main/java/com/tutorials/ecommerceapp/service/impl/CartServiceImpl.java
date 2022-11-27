package com.tutorials.ecommerceapp.service.impl;

import com.tutorials.ecommerceapp.dto.cart.AddToCartDto;
import com.tutorials.ecommerceapp.dto.cart.CartItems;
import com.tutorials.ecommerceapp.dto.cart.ViewCartResponse;
import com.tutorials.ecommerceapp.exception.CartException;
import com.tutorials.ecommerceapp.exception.ProductException;
import com.tutorials.ecommerceapp.mapper.CartMapper;
import com.tutorials.ecommerceapp.model.Cart;
import com.tutorials.ecommerceapp.model.Product;
import com.tutorials.ecommerceapp.repository.CartRepository;
import com.tutorials.ecommerceapp.repository.ProductRepository;
import com.tutorials.ecommerceapp.service.AuthService;
import com.tutorials.ecommerceapp.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    private final AuthService authService;

    private final CartMapper cartMapper;


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
    public List<ViewCartResponse> viewCart() {
        List<Cart> carts = cartRepository.findByUser(authService.getCurrentUser());

        if (carts.isEmpty()) {
            throw new CartException("Cart is Empty, please add items");
        }

        List<CartItems> cartItemsList = cartMapper.mapCartToCartItemsList(carts);


    }

    private void calculateTotalCost(List<CartItems> cartItemsList){
        cartItemsList.forEach(
                cartItems -> cartItems.getQuantity() *
        );
    }
}
