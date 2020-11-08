package com.esisba.webservice.repositories;

import com.esisba.webservice.entitiy.Order;
import com.esisba.webservice.entitiy.OrderItem;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RibbonClient(name="order-service")
@FeignClient(contextId = "orderItemProxy", name="order-service")
public interface OrderItemProxy {
    @GetMapping("/api-v1/order-items/{id}")
    OrderItem getOrderItemById(@PathVariable("id") Long id);
}
