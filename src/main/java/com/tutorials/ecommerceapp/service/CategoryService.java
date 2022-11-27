package com.tutorials.ecommerceapp.service;

import com.tutorials.ecommerceapp.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    void createCategory(CategoryDto categoryDto);

    List<CategoryDto> getCategories();

    void updateCategory(CategoryDto categoryDto);
}
