package com.baorant.configclient.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * Git配置类
 * 从配置服务器动态读取 data.env 等配置信息。
 */
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "data")
public class GitConfig {

    private String env = "Default"; // 默认值

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    @Override
    public String toString() {
        return "GitConfig{env='" + env + "'}";
    }
}
