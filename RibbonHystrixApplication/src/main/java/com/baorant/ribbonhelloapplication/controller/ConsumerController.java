package com.baorant.ribbonhelloapplication.controller;

import com.baorant.ribbonhelloapplication.service.UserServiceClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    private final UserServiceClient userServiceClient;

    public ConsumerController(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    @GetMapping("/testSayHi")
    public String testSayHi(@RequestParam(value = "sleep_seconds", required = true) int sleep_seconds) {
        return userServiceClient.sayHi(sleep_seconds);
    }
}