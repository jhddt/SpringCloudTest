package com.example.feigncloudclientapplication.controller;

import com.example.feigncloudclientapplication.service.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private UserClient userClient;

    @RequestMapping("/sayHi")
    public String hello(@RequestParam(value = "sleep_seconds") int sleep_seconds) {
        return userClient.hello(sleep_seconds);
    }
}

