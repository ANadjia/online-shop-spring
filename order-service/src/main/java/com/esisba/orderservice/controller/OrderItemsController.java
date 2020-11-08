package com.esisba.orderservice.controller;


import com.esisba.orderservice.entitiy.Order;
import com.esisba.orderservice.entitiy.OrderItem;
import com.esisba.orderservice.entitiy.Product;
import com.esisba.orderservice.repository.OrderItemRepository;
import com.esisba.orderservice.repository.OrderRepository;
import com.esisba.orderservice.repository.ProductProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api-v1/order-items")
public class OrderItemsController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductProxy productProxy;

    @GetMapping("/")
    public Collection<OrderItem> getAllOrders(){
        return orderItemRepository.findAll();
    }

    @GetMapping("/{idOrderItem}")
    public OrderItem getOrderItem(@PathVariable("idOrderItem") Long idOrderItem){
        return orderItemRepository.findById(idOrderItem).get();
    }

    @PostMapping("/")
    public OrderItem createOrderItem(@Valid @RequestBody OrderItem order){
        return orderItemRepository.save(order);
    }

    @PutMapping("{idOrder}")
    public OrderItem updateOrderItem(@PathVariable("idOrder") Long idOrder, @Valid @RequestBody OrderItem order){
        if(orderRepository.findById(idOrder).isPresent()){
            order.setIdOrderItem(idOrder);
            return orderItemRepository.save(order);
        }
        return null;
    }

    @DeleteMapping("{idOrder}")
    public void deleteOrderItem(@PathVariable("idOrder") Long idOrder){
        if(orderItemRepository.findById(idOrder).isPresent()){
            orderItemRepository.deleteById(idOrder);
        }
    }

    @GetMapping("/{idOrder}/product")
    public Product getProductById(@PathVariable("idOrder") Long idOrder){
        OrderItem order = orderItemRepository.findById(idOrder).get();
        System.out.println("UserId " +order.getProductId());
        return productProxy.getProduitById(order.getProductId());
    }


}