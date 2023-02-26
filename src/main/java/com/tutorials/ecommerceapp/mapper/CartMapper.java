package com.tutorials.ecommerceapp.mapper;

import com.tutorials.ecommerceapp.dto.product.ProductDto;
import com.tutorials.ecommerceapp.dto.cart.CartItems;
import com.tutorials.ecommerceapp.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CartMapper {

    @Autowired
    private ProductMapper productMapper;

    @Mapping(target = "cartId", source = "id")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "product", expression = "java(mapProductToDto(cart))")
    public abstract CartItems mapCartToCartItems(Cart cart);

    @Mapping(target = "cartId", source = "id")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "product", expression = "java(mapProductToDto(cart))")
    public abstract List<CartItems> mapCartToCartItemsList(List<Cart> cartList);

    ProductDto mapProductToDto(Cart cart) {
        return productMapper.mapProductToDto(cart.getProduct());
    }
}
