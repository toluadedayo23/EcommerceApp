package com.tutorials.ecommerceapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    @NotBlank
    private String name;

    @NotBlank
    private String imageURL;

    @NotBlank
    private String description;

    @NotEmpty
    private double price;

    @NotEmpty
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long categoryId;


}
