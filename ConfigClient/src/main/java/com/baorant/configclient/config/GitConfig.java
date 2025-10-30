package com.baorant.configclient.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope // 支持动态刷新
public class GitConfig {
    @Value("${data.env:Default}")
    public String env;

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }
}