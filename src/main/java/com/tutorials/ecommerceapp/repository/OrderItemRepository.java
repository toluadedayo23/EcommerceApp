package com.tutorials.ecommerceapp.repository;

import com.tutorials.ecommerceapp.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
