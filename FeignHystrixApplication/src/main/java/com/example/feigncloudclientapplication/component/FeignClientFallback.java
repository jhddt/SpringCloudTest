package com.example.feigncloudclientapplication.component;

import com.example.feigncloudclientapplication.service.UserClient;
import org.springframework.stereotype.Component;

@Component
public class FeignClientFallback implements UserClient {
    @Override
    public String hello(int sleep_seconds) {
        return "Hi!容错机制已经启动";
    }
}

