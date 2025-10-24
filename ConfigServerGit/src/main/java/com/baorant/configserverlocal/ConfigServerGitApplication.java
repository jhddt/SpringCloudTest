package com.baorant.configserverlocal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Git配置服务器应用启动类
 * 该应用作为Spring Cloud Config Server，从Git仓库获取配置文件并提供配置管理功能
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigServerGitApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerGitApplication.class, args);
    }
}