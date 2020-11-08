package com.esisba.orderservice.repository;


import com.esisba.orderservice.entitiy.User;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RibbonClient(name="account-service")
@FeignClient(contextId = "userProxy", name="account-service")
public interface UserProxy {

    @GetMapping("/api-v1/users/user/{id}")
    User getUserById(@PathVariable("id") Long id);

}