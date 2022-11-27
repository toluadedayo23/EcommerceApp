package com.tutorials.ecommerceapp.mapper;

import com.tutorials.ecommerceapp.dto.CategoryDto;
import com.tutorials.ecommerceapp.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {


    CategoryDto mapCategoryToDto(Category category);

    @Mapping(target = "id", ignore = true)
    Category map(CategoryDto categoryDto);

}
