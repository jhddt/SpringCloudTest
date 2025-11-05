package com.baorant.configclient.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * Git配置类
 * 从配置服务器动态读取 data.env 等配置信息。
 *
 * 特点：
 * ✅ 使用 @ConfigurationProperties 自动绑定整个 data 前缀
 * ✅ 兼容 @RefreshScope，刷新后能立即生效
 * ✅ 不依赖硬编码 @Value
 */
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "data")
public class GitConfig {

    /**
     * 环境名称，例如 dev / pro / test / git-dev-fixed 等。
     */
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
