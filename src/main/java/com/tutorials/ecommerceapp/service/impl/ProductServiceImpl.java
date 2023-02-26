package com.tutorials.ecommerceapp.service.impl;

import com.tutorials.ecommerceapp.dto.product.ProductDto;
import com.tutorials.ecommerceapp.dto.product.UpdateProductDto;
import com.tutorials.ecommerceapp.exception.CategoryException;
import com.tutorials.ecommerceapp.exception.ProductException;
import com.tutorials.ecommerceapp.mapper.ProductMapper;
import com.tutorials.ecommerceapp.model.Category;
import com.tutorials.ecommerceapp.model.Product;
import com.tutorials.ecommerceapp.repository.CategoryRepository;
import com.tutorials.ecommerceapp.repository.ProductRepository;
import com.tutorials.ecommerceapp.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new CategoryException("Category", productDto.getCategoryId()));
        if(productRepository.existsByName(productDto.getName())){
            throw new ProductException("Product: " + productDto.getName()+ " already exists, please create a new one or update the existing one");
        }

        Product product = productMapper.map(productDto);
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);
        return productMapper.mapProductToDto(savedProduct);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> productList = productRepository.findAll();

        return productList.stream()
                .map(productMapper::mapProductToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto updateProduct(UpdateProductDto updateProductDto) {
        if(!productRepository.existsByName(updateProductDto.getName())){
            throw new ProductException("Product", updateProductDto.getName());
        }
        Product product = productRepository.findByName(updateProductDto.getName())
                .orElseThrow(() -> new ProductException("Product", updateProductDto.getName()));

        product.setName(updateProductDto.getNewName());
        product.setImageURL(updateProductDto.getImageURL());
        product.setDescription(updateProductDto.getDescription());
        product.setPrice(updateProductDto.getPrice());

        Product savedProduct = productRepository.save(product);

        return productMapper.mapProductToDto(savedProduct);
    }
}
