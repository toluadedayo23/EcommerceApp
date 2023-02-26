package com.tutorials.ecommerceapp.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductDto {

    @NotBlank
    private String name;

    @NotBlank
    private String newName;

    @NotBlank
    private String imageURL;

    @NotBlank
    private String description;


    private double price;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long categoryId;
}
