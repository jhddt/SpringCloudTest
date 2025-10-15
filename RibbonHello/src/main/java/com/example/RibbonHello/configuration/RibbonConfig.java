package com.example.RibbonHello.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

/**
 * 配置Ribbon
 */
@Configuration
public class RibbonConfig {

    /**
     * 使用随机的轮询方法
     *
     * @return
     */
    @Bean
    @LoadBalanced
    public IRule iRule() {
        return new RandomRule();
    }
}
