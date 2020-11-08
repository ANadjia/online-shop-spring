package com.esisba.webservice.repositories;


import com.esisba.webservice.entitiy.Order;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RibbonClient(name="order-service")
@FeignClient(contextId = "orderProxy", name="order-service")
public interface OrderProxy {


    @GetMapping("/api-v1/orders/{id}")
    Order getOrderById(@PathVariable("id") Long id);

    @GetMapping("/api-v1/orders/user/{id}")
    List<Order> getOrdersByUser(@PathVariable("id") Long id);

}
