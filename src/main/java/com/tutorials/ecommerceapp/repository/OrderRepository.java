package com.tutorials.ecommerceapp.repository;

import com.tutorials.ecommerceapp.model.Order;
import com.tutorials.ecommerceapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserOrderByCreatedDateDesc(User user);
}
