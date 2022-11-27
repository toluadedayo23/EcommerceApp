package com.tutorials.ecommerceapp.mapper;

import com.tutorials.ecommerceapp.dto.ProductDto;
import com.tutorials.ecommerceapp.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto mapProductToDto(Product product);

    @Mapping(target = "id", ignore = true)
    Product map(ProductDto productDto);
}
