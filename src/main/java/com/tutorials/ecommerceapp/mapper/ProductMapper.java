package com.tutorials.ecommerceapp.mapper;

import com.tutorials.ecommerceapp.dto.ProductDto;
import com.tutorials.ecommerceapp.model.Product;
import com.tutorials.ecommerceapp.model.WishList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto mapProductToDto(Product product);

    @Mapping(target = "id", ignore = true)
    Product map(ProductDto productDto);

    @Mapping(target = "name", expression = "java(wishList.getProduct().getName())")
    @Mapping(target = "imageURL", expression = "java(wishList.getProduct().getImageURL())")
    @Mapping(target = "description", expression = "java(wishList.getProduct().getDescription())")
    @Mapping(target = "price", expression = "java(wishList.getProduct().getPrice())")
    ProductDto mapWishListToProduct(WishList wishList);
}
