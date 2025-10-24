package com.baorant.redistopicqueue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Redis消息发布控制器
 */
@RestController
@RequestMapping("/redis")
public class RedisPublisher {
    @Autowired
    private StringRedisTemplate template;

    /**
     * 发布消息到Redis主题
     *
     * @return 操作结果
     */
    @RequestMapping("/publish")
    public String publish() {
        for (int i = 1; i <= 10; i++) {
            template.convertAndSend("mytopic", "这是我发第" + i + "条的消息...");
        }
        return "结束";
    }

}