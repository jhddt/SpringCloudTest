package com.example.feigncloudclientapplication.controller;

import com.example.feigncloudclientapplication.service.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 测试控制器类，用于处理Feign客户端调用请求
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private UserClient userClient;
    /**
     * 调用用户服务的sayHi接口
     * @param sleep_seconds 睡眠秒数参数，用于模拟服务延迟
     * @return 用户服务返回的响应字符串
     */
    @RequestMapping("/sayHi")
    public String hello(@RequestParam(value = "sleep_seconds") int sleep_seconds) {
        return userClient.hello(sleep_seconds);
    }
}