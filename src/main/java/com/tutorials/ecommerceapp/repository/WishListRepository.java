package com.tutorials.ecommerceapp.repository;

import com.tutorials.ecommerceapp.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<WishList, Long> {
}
