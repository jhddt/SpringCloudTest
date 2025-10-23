package com.example.feigncloudclientapplication.service;

import com.example.feigncloudclientapplication.component.FeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户服务Feign客户端接口，用于调用远程UserService服务
 */
@FeignClient (name = "UserService", fallback = FeignClientFallback.class)
public interface UserClient {
    /**
     * 调用远程UserService的sayHi接口
     *
     * @param sleep_seconds 睡眠秒数参数，用于模拟服务延迟
     * @return 远程服务返回的响应字符串
     */
    @RequestMapping(value = "/user/sayHi")
    public String hello(@RequestParam(value = "sleep_seconds") int sleep_seconds);
}