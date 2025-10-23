package com.baorant.ribbonhelloapplication.controller;

import com.baorant.ribbonhelloapplication.service.UserServiceClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消费者控制器类，用于处理用户服务的调用请求
 */
@RestController
public class ConsumerController {

    private final UserServiceClient userServiceClient;

    /**
     * 构造函数，初始化用户服务客户端
     *
     * @param userServiceClient 用户服务客户端实例
     */
    public ConsumerController(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    /**
     * 测试调用用户服务的sayHi接口
     *
     * @param sleep_seconds 睡眠秒数参数，用于模拟服务延迟
     * @return 用户服务返回的响应字符串
     */
    @GetMapping("/testSayHi")
    public String testSayHi(@RequestParam(value = "sleep_seconds", required = true) int sleep_seconds) {
        return userServiceClient.sayHi(sleep_seconds);
    }
}