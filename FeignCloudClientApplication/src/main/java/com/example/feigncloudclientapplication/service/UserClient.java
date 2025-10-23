package com.example.feigncloudclientapplication.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * UserClient接口
 * 通过Feign声明式REST客户端调用UserService服务
 */
@FeignClient(name = "UserService")
public interface UserClient {
    /**
     * 调用UserService的sayHi接口
     * @param sleepSeconds 睡眠秒数参数，必须提供
     * @return 来自UserService的响应字符串
     */
    @RequestMapping(value = "/user/sayHi", method = RequestMethod.GET)
    String hello(@RequestParam(value = "sleep_seconds", required = true) int sleepSeconds);
}