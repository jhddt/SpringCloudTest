package com.baorant.configclient.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * Git配置类
 * 用于从配置服务器获取Git仓库中的配置信息
 * 
 * 配置刷新说明：
 * 1. 该类使用@RefreshScope注解，支持配置的动态刷新
 * 2. 当Git仓库中的配置发生变化时，可通过/actuator/refresh端点刷新配置
 * 3. 如果配置值为null，可能原因：
 *    - Git仓库中缺少对应的配置项
 *    - 配置服务器未正确读取Git仓库的配置文件
 *    - ConfigClient未正确连接到配置服务器
 */
@Configuration
@RefreshScope // 支持动态刷新
public class GitConfig {
    /**
     * 环境配置属性
     * 从配置服务器获取data.env的值，如果获取不到则使用默认值"Default"
     * 配置来源优先级：
     * 1. Git仓库中的配置文件（最高优先级）
     * 2. ConfigClient本地的bootstrap.yml
     * 3. ConfigClient本地的application.yml（最低优先级）
     */
    @Value("${data.env:Default}")
    public String env;

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }
}