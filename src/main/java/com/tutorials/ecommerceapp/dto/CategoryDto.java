package com.tutorials.ecommerceapp.dto;

import com.tutorials.ecommerceapp.dto.ProductDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class CategoryDto {

    @NotBlank
    private String categoryName;

    @NotBlank
    private String newCategoryName;

    @NotBlank
    private String description;;

    @NotBlank
    private String imageUrl;

    @NotEmpty
    Set<ProductDto> products;

}
