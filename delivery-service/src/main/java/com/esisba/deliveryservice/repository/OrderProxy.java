package com.esisba.deliveryservice.repository;

import com.esisba.deliveryservice.entitiy.Order;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RibbonClient(name="order-service")
@FeignClient(contextId = "orderProxy", name="order-service")
public interface OrderProxy {

    @GetMapping("/api-v1/orders/{id}")

    Order getOrderById(@PathVariable("id") Long id);
}
