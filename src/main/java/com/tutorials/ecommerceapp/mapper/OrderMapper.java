package com.tutorials.ecommerceapp.mapper;

import com.tutorials.ecommerceapp.dto.order.OrderDto;
import com.tutorials.ecommerceapp.dto.order.OrderItemDto;
import com.tutorials.ecommerceapp.model.Order;
import com.tutorials.ecommerceapp.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {


    @Mapping(target = "createdDate", source = "createdDate")
    @Mapping(target = "totalPrice", source = "totalPrice")
    @Mapping(target = "sessionId", source = "sessionId")
    OrderDto mapOrderToDto(Order order);

}
