package com.tutorials.ecommerceapp.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class CreateOrderDto {

    @Range(min = 1, message = "userId cannot be lesser than 1")
    private Long userId;

    @NotBlank
    private String sessionId;
}
