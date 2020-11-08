package com.esisba.orderservice.repository;

import com.esisba.orderservice.entitiy.Order;
import com.esisba.orderservice.entitiy.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findOrderItemByOrder(Order order);

}