package com.baorant.redistopicqueue.config;

import com.baorant.redistopicqueue.listener.Receiver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * Redis配置类，用于配置消息监听容器、消息监听适配器和消息接收者
 */
@Configuration
public class MyRedisConf {
    /**
     * 创建Redis消息监听容器
     * 
     * @param connectionFactory Redis连接工厂
     * @param listenerAdapter 消息监听适配器
     * @return Redis消息监听容器
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                                   MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("mytopic"));
        return container;
    }

    /**
     * 创建消息监听适配器
     * 
     * @param receiver 消息接收者
     * @return 消息监听适配器
     */
    @Bean
    public MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    /**
     * 创建消息接收者实例
     * 
     * @return 消息接收者
     */
    @Bean
    public Receiver receiver() {
        return new Receiver();
    }
}