package com.tutorials.ecommerceapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryDto {

    @NotBlank
    private String categoryName;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String newCategoryName;

    @NotBlank
    private String description;;

    @NotBlank
    private String imageUrl;

}
