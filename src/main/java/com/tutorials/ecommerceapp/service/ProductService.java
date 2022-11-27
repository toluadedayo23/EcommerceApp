package com.tutorials.ecommerceapp.service;

import com.tutorials.ecommerceapp.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);

    List<ProductDto> getAllProducts();

    ProductDto updateProduct(ProductDto productDto);
}
