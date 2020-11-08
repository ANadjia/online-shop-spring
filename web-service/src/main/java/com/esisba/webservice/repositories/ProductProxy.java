package com.esisba.webservice.repositories;

import com.esisba.webservice.entitiy.Product;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RibbonClient(name="product-service")
@FeignClient(contextId = "productProxy", name="product-service")
public interface ProductProxy {
    @GetMapping("/api-v1/products/{id}")
    Product getProductById(@PathVariable("id") Long id);
}
