package com.tutorials.ecommerceapp.service.impl;

import com.tutorials.ecommerceapp.dto.CategoryDto;
import com.tutorials.ecommerceapp.exception.CategoryException;
import com.tutorials.ecommerceapp.mapper.CategoryMapper;
import com.tutorials.ecommerceapp.mapper.ProductMapper;
import com.tutorials.ecommerceapp.model.Category;
import com.tutorials.ecommerceapp.repository.CategoryRepository;
import com.tutorials.ecommerceapp.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public void createCategory(CategoryDto categoryDto) {
        Category savedCategory = categoryMapper.map(categoryDto);
        categoryRepository.save(savedCategory);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(categoryMapper::mapCategoryToDto).collect(Collectors.toList());
    }

    @Override
    public void updateCategory(CategoryDto categoryDto) {
        Category category = categoryRepository.findByCategoryName(categoryDto.getCategoryName()).orElseThrow(() -> new CategoryException("Category", categoryDto.getCategoryName()));

        if (categoryDto.getNewCategoryName() != null & categoryDto.getNewCategoryName().length() > 2) {
            category.setCategoryName(categoryDto.getNewCategoryName());
        }
        category.setDescription(categoryDto.getDescription());
        category.setImageUrl(categoryDto.getImageUrl());
        categoryRepository.save(category);
    }


}
