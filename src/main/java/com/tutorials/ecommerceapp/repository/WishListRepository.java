package com.tutorials.ecommerceapp.repository;

import com.tutorials.ecommerceapp.model.User;
import com.tutorials.ecommerceapp.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishList, Long> {

    List<WishList> findAllByUserOrderByCreatedDateDesc(User user);

    List<WishList> findAllByUser(User user);
}
