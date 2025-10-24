package com.baorant.configserverlocal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 本地配置服务器应用启动类
 * 该应用作为Spring Cloud Config Server，提供配置文件的集中管理功能
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigServerLocalApplication {
    /**
     * 应用程序入口点
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerLocalApplication.class, args);
    }
}