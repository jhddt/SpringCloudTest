package com.example.feigncloudclientapplication.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "UserService")
public interface UserClient {
    // 参数名显式指定为sleep_seconds，类型为int（与UserService一致）
    @RequestMapping(value = "/user/sayHi", method = RequestMethod.GET)
    String hello(@RequestParam(value = "sleep_seconds", required = true) int sleepSeconds);
}


