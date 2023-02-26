package com.tutorials.ecommerceapp.service;

import com.tutorials.ecommerceapp.dto.product.ProductDto;
import com.tutorials.ecommerceapp.dto.product.UpdateProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);

    List<ProductDto> getAllProducts();

    ProductDto updateProduct(UpdateProductDto updateProductDto);
}
