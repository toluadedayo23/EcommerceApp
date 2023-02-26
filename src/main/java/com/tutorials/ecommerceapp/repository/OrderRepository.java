package com.tutorials.ecommerceapp.repository;

import com.tutorials.ecommerceapp.model.Order;
import com.tutorials.ecommerceapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT o.order_id, o.created_date, o.session_id, \n" +
            "o.total_price, oi.price, oi.quantity, p.name\n" +
            "from orders as o\n" +
            "join order_items as oi\n" +
            "on o.order_id = oi.order_id\n" +
            "join products  as p\n" +
            "on oi.product_id = p.id\n" +
            "where o.user_id = :userId\n" +
            "order by created_date desc\n" +
            "limit 10", nativeQuery = true)
    List<Tuple> getLastTenOrders(@Param("userId") Long userId);


    @Query(value = "SELECT o.order_id, o.created_date, o.session_id, \n" +
            "o.total_price, oi.price, oi.quantity, p.name\n" +
            "from orders as o\n" +
            "join order_items as oi\n" +
            "on o.order_id = oi.order_id\n" +
            "join products  as p\n" +
            "on oi.product_id = p.id\n" +
            "where o.user_id = :userId and o.order_id = :orderId\n" +
            "order by created_date desc", nativeQuery = true)
    List<Tuple> getOrderByIdAndUserId(@Param("orderId") Long orderId,
                                      @Param("userId") Long userId);
}
