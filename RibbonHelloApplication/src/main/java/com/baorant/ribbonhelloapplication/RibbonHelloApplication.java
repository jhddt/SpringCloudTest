package com.baorant.ribbonhelloapplication;

import com.baorant.ribbonhelloapplication.config.RibbonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * Ribbon负载均衡示例应用启动类
 * 该应用启用了服务发现功能，并配置了针对UserService的Ribbon客户端
 */
@SpringBootApplication
@EnableDiscoveryClient
@RibbonClient(name = "UserService",configuration = RibbonConfig.class)
public class RibbonHelloApplication {
    public static void main(String[] args) {
        SpringApplication.run(RibbonHelloApplication.class, args);
    }
}