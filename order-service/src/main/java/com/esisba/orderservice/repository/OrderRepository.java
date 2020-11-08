package com.esisba.orderservice.repository;


import com.esisba.orderservice.entitiy.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> getOrdersByUserId(Long userId);
}