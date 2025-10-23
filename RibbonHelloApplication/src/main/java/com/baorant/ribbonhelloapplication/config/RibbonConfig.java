package com.baorant.ribbonhelloapplication.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Ribbon配置类
 * 配置负载均衡策略等相关配置
 */
@Configuration
public class RibbonConfig {
    // 注入RestTemplate，添加@LoadBalanced启用Ribbon负载均衡
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}