package com.example.feigncloudclientapplication.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "UserService")
public interface UserClient {
    @RequestMapping(value = "/user/sayHi")
    public String hello();
}


