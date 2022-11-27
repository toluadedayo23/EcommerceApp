package com.tutorials.ecommerceapp.controller;

import com.tutorials.ecommerceapp.dto.ProductDto;
import com.tutorials.ecommerceapp.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto){
        return productService.createProduct(productDto);
    }

    @GetMapping
    public List<ProductDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @PutMapping("/update")
    public ProductDto updateProduct(@RequestBody ProductDto productDto){
        return productService.updateProduct(productDto);
    }
}
