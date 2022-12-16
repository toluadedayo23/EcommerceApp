package com.tutorials.ecommerceapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class CreateWishListDto {

    @NotEmpty
    private Long productId;
}
