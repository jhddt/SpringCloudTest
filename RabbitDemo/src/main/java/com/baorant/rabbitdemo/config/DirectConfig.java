package com.baorant.rabbitdemo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ直连交换机配置类
 * 用于配置直连交换机相关的队列、交换机和绑定关系
 */
@Configuration
public class DirectConfig {
    /**
     * 创建直连队列
     * @return 直连队列实例
     */
    @Bean
    public Queue directQueue() {
        // 队列名字为"direct"，不持久化
        return new Queue("direct", false);
    }
    
    /**
     * 创建直连交换机
     * @return 直连交换机实例
     */
    @Bean
    public DirectExchange directExchange() {
        // 交换器名称为"direct"，不持久化，不自动删除
        return new DirectExchange("direct", false, false);
    }

    /**
     * 绑定队列到交换机
     * @param queue 需要绑定的队列
     * @param exchange 需要绑定到的交换机
     * @return 绑定关系实例
     */
    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("direct");
    }
}