package com.tutorials.ecommerceapp.dto.cart;

import com.tutorials.ecommerceapp.dto.product.ProductDto;
import lombok.Data;

@Data
public class CartItems {
    private Long cartId;
    private Integer quantity;
    private ProductDto product;
}
