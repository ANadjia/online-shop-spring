package com.esisba.webservice.repositories;

import com.esisba.webservice.entitiy.Delivery;
import com.esisba.webservice.entitiy.Product;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RibbonClient(name="delivery-service")
@FeignClient(contextId = "deliveryProxy", name="delivery-service")
public interface DeliveryProxy {
    @GetMapping("/api-v1/delivery/{id}")
    Delivery getProductById(@PathVariable("id") Long id);

}
