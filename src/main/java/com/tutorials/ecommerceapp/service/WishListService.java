package com.tutorials.ecommerceapp.service;

import com.tutorials.ecommerceapp.dto.CreateWishListDto;
import com.tutorials.ecommerceapp.dto.product.ProductDto;

import java.util.List;

public interface WishListService {

    void createWishList(CreateWishListDto createWishListDto);

    List<ProductDto> getWishList();

    void removeFromWishList(Long wishListId);

    void clearWishList();
}
