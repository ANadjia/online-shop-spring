package com.esisba.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class WebServiceApplication {

    public static final String APP_URL = "/";
    public static final String LOGIN_URL = "/login";
    public static final String LOGIN_FAILURE_URL = "/login-error";

    public static void main(String[] args) {
        SpringApplication.run(WebServiceApplication.class, args);
    }

}
