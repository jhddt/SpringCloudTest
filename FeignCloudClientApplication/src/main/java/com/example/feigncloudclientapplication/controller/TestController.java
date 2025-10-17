package com.example.feigncloudclientapplication.controller;

import com.example.feigncloudclientapplication.service.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private UserClient userClient;

    // 接收参数类型为int，与UserService和UserClient一致
    @RequestMapping(value = "/sayHi", method = RequestMethod.GET)
    public String hello(@RequestParam(value = "sleep_seconds", required = true) int sleepSeconds) throws InterruptedException {
        // 直接传递int类型参数给UserClient
        return userClient.hello(sleepSeconds);
    }
}
