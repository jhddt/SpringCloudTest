package com.baorant.configclient.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Config Client 启动信息监听器
 *
 * 用于在客户端启动成功后，打印从 Config Server / Git 加载的配置来源和主要属性，
 * 帮助快速判断配置是否成功获取。
 */
@Component
@RefreshScope
public class ConfigStartupLogger implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger log = LoggerFactory.getLogger(ConfigStartupLogger.class);

    @Autowired
    private Environment env;

    @Autowired
    private GitConfig gitConfig;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("\n\n==============================================");
        log.info("Config Client 启动成功并加载远程配置！");
        log.info("应用名: {}", env.getProperty("spring.application.name", "未知"));
        log.info("当前环境 (profile): {}", env.getProperty("spring.profiles.active", "未定义"));
        log.info("使用分支 (label): {}", env.getProperty("spring.cloud.config.label", "未定义"));
        log.info("远程配置路径: {}", env.getProperty("spring.cloud.config.uri", "未定义"));
        log.info("当前 data.env: {}", gitConfig.getEnv());
        log.info("==============================================\n");
    }
}
