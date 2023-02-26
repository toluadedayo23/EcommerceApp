package com.tutorials.ecommerceapp.controller;

import com.tutorials.ecommerceapp.dto.product.ProductDto;
import com.tutorials.ecommerceapp.dto.product.UpdateProductDto;
import com.tutorials.ecommerceapp.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/product/")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ProductDto createProduct(@Valid @RequestBody ProductDto productDto){
        return productService.createProduct(productDto);
    }

    @GetMapping("all")
    public List<ProductDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @PutMapping("update")
    @PreAuthorize("hasRole('ADMIN')")
    public ProductDto updateProduct(@Valid @RequestBody UpdateProductDto updateProductDto){
        return productService.updateProduct(updateProductDto);
    }
}
