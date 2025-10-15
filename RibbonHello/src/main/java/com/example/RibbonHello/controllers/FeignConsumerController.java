package com.example.RibbonHello.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Feign/consumer")
public class FeignConsumerController {
    @Autowired
    LoadBalancerClient loadBalancerClient;
    
    @GetMapping("/hello")
    public String hello(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("UserService");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/hi";
        System.out.println(url);
        return url;
    }
}
