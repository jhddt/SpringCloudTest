package com.baorant.rabbitdemo.component;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RabbitMQ消息发送者类
 * 用于向RabbitMQ发送消息到指定队列
 */
@Component
public class Sender {
    @Autowired
    AmqpTemplate rabbitmqTemplate;

    /**
     * 发送消息到指定队列
     * @param message 要发送的消息内容
     */
    public void send(String message){
        System.out.println("发送消息："+message);
        rabbitmqTemplate.convertAndSend("direct",message);
    }
}