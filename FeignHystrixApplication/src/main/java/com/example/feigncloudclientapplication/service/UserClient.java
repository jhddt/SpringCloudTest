package com.example.feigncloudclientapplication.service;

import com.example.feigncloudclientapplication.component.FeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient (name = "UserService", fallback = FeignClientFallback.class)
public interface UserClient {
    @RequestMapping(value = "/user/sayHi")
    public String hello(@RequestParam(value = "sleep_seconds") int sleep_seconds);
}



