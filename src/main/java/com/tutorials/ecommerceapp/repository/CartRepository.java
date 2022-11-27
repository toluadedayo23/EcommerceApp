package com.tutorials.ecommerceapp.repository;

import com.tutorials.ecommerceapp.model.Cart;
import com.tutorials.ecommerceapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUser(User currentUser);
}
