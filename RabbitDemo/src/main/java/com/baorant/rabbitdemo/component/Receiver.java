package com.baorant.rabbitdemo.component;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * RabbitMQ消息接收者类
 * 监听并处理来自"direct"队列的消息
 */
@Component
@RabbitListener(queues = "direct")
public class Receiver {

    /**
     * 处理接收到的消息
     * @param message 接收到的消息内容
     */
    @RabbitHandler
    public void handler(String message){
        System.out.println("接收消息："+message);
    }
}