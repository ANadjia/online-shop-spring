package com.esisba.orderservice.controller;

import com.esisba.orderservice.entitiy.Order;
import com.esisba.orderservice.entitiy.OrderItem;
import com.esisba.orderservice.entitiy.Product;
import com.esisba.orderservice.entitiy.User;
import com.esisba.orderservice.repository.OrderItemRepository;
import com.esisba.orderservice.repository.OrderRepository;
import com.esisba.orderservice.repository.ProductProxy;
import com.esisba.orderservice.repository.UserProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api-v1/orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserProxy userProxy;

    @Autowired
    private ProductProxy productProxy;

    @GetMapping("/")
    public Collection<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    @GetMapping("/{idOrder}")
    public Order getOrder(@PathVariable("idOrder") Long idOrder){

        System.out.println("idOrder: " +idOrder);
        return orderRepository.findById(idOrder).get();
    }

    @PostMapping("/")
    public Order createOrder(@Valid @RequestBody Order order){
        Order o = order;
        int i = 20;
        return orderRepository.save(order);
    }

    @PutMapping("{idOrder}")
    public Order updateOrder(@PathVariable("idOrder") Long idOrder, @Valid @RequestBody Order order){
        if(orderRepository.findById(idOrder).isPresent()){
            order.setIdOrder(idOrder);
            return orderRepository.save(order);
        }
        return null;
    }

    @DeleteMapping("{idOrder}")
    public void deleteOrder(@PathVariable("idOrder") Long idOrder){
        if(orderRepository.findById(idOrder).isPresent()){
            orderRepository.deleteById(idOrder);
        }
    }

    @GetMapping("/{idOrder}/items")
    public List<OrderItem> getCategoryProducts(@PathVariable("idOrder") Long idOrder){
        Order order = orderRepository.findById(idOrder).get();
        return orderItemRepository.findOrderItemByOrder(order);
    }

    @GetMapping("/{idOrder}/user")
    public User getUserById(@PathVariable("idOrder") Long idOrder){
        Order order = orderRepository.findById(idOrder).get();
        System.out.println("UserId " +order.getUserId());
        return userProxy.getUserById(order.getUserId());
    }

    @GetMapping("/user/{id}")
    public List<Order> getUserOrders(@PathVariable("id") Long idUser){
        return orderRepository.getOrdersByUserId(idUser);
    }

}