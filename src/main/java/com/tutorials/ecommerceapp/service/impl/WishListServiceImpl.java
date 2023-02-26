package com.tutorials.ecommerceapp.service.impl;

import com.tutorials.ecommerceapp.dto.CreateWishListDto;
import com.tutorials.ecommerceapp.dto.product.ProductDto;
import com.tutorials.ecommerceapp.exception.ProductException;
import com.tutorials.ecommerceapp.exception.WishListException;
import com.tutorials.ecommerceapp.mapper.ProductMapper;
import com.tutorials.ecommerceapp.model.Product;
import com.tutorials.ecommerceapp.model.User;
import com.tutorials.ecommerceapp.model.WishList;
import com.tutorials.ecommerceapp.repository.ProductRepository;
import com.tutorials.ecommerceapp.repository.WishListRepository;
import com.tutorials.ecommerceapp.service.AuthService;
import com.tutorials.ecommerceapp.service.WishListService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WishListServiceImpl implements WishListService {

    private final ProductRepository productRepository;
    private final AuthService authService;
    private final WishListRepository wishListRepository;
    private final ProductMapper productMapper;

    @Transactional
    @Override
    public void createWishList(CreateWishListDto createWishListDto) {
        Product product = productRepository.findById(createWishListDto.getProductId())
                .orElseThrow(() -> new ProductException("Product with productId: "
                        + createWishListDto.getProductId() + " not found"));

        User currentUser = authService.getCurrentUser();

        WishList wishList = new WishList();
        wishList.setUser(currentUser);
        wishList.setCreatedDate(new Date());
        wishList.setProduct(product);

        wishListRepository.save(wishList);
    }

    @Override
    public List<ProductDto> getWishList() {

        List<WishList> wishLists = wishListRepository.findAllByUserOrderByCreatedDateDesc(authService.getCurrentUser());

        return wishLists.stream()
                .map(productMapper::mapWishListToProduct)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void removeFromWishList(Long wishListId) {
        WishList wishList = wishListRepository.findById(wishListId)
                .orElseThrow(() -> new WishListException("WishList", wishListId));

        wishListRepository.delete(wishList);

    }

    @Transactional
    @Override
    public void clearWishList() {
        List<WishList> wishLists = wishListRepository.findAllByUser(authService.getCurrentUser());
        wishListRepository.deleteAll(wishLists);
    }
}