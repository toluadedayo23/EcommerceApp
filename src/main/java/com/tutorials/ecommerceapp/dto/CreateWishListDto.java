package com.tutorials.ecommerceapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateWishListDto {

    @Range(min = 1, message = "productId cannot be lesser than 1")
    private Long productId;
}
