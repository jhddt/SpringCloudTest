package com.baorant.configclient.controller;

import com.baorant.configclient.config.GitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置测试控制器
 * 用于测试从配置服务器获取的配置信息
 */
@RestController
@RefreshScope
@RequestMapping("/test")
public class TestController {
    @Autowired
    GitConfig gitConfig;

    /**
     * 显示当前环境配置
     * 该接口用于展示从Git配置仓库获取的环境信息
     * 如果显示为null，可能的原因：
     * 1. 配置服务器未正确启动或配置文件未更新
     * 2. ConfigClient未正确连接到配置服务器
     * 3. 配置刷新未生效或配置属性名称不匹配
     * 4. Git仓库中的配置文件格式或内容有误
     * 
     * @return 当前环境配置信息
     */
    @GetMapping("/show")
    public String show() {
        return "Current Environment: " + gitConfig.env;
    }
    
    /**
     * 健康检查接口
     * 用于检查ConfigClient服务是否正常运行
     * 
     * @return 服务运行状态信息
     */
    @GetMapping("/health")
    public String health() {
        return "ConfigClient is running";
    }
    
    /**
     * 获取详细配置信息接口
     * 以JSON格式返回当前的配置信息
     * 
     * @return 包含环境配置的Map对象
     */
    @GetMapping("/config")
    public Map<String, String> config() {
        Map<String, String> configMap = new HashMap<>();
        configMap.put("env", gitConfig.env);
        return configMap;
    }
}