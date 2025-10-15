package com.baorant.ribbonhelloapplication.controller;

import com.baorant.ribbonhelloapplication.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
    @Autowired
    LoadBalancerClient loadBalancerClient;

    @RequestMapping("/hello")
    public String hello(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("UserService");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/hi";
        System.out.println(url);
        return url;
    }

    @RequestMapping("/userdetails")
    public String userdetails(@RequestParam(value = "userName", required = true) String _username) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("UserService");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/user/user?userName="
                + _username;
        System.out.println("url="+url);
        RestTemplate restTemplate = new RestTemplate();
        User u = restTemplate.getForObject(url, User.class);
        if (u == null)
            return "没有找到用户记录";
        else
            return u.toString();
    }
}


