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
 * 用于验证 ConfigClient 是否能正确加载远程 Git 仓库的配置
 */
@RestController
@RefreshScope
@RequestMapping("/test")
public class TestController {

    @Autowired
    private GitConfig gitConfig;

    /**
     * 健康检查接口
     */
    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "ConfigClient is running normally.");
        result.put("timestamp", System.currentTimeMillis());
        return result;
    }

    /**
     * 显示当前环境配置
     */
    @GetMapping("/show")
    public Map<String, Object> show() {
        Map<String, Object> result = new HashMap<>();
        String env = gitConfig.getEnv(); // ✅ 用 getter 访问

        if (env == null || env.equals("Default")) {
            result.put("code", 500);
            result.put("message", "配置未从 Git 仓库正确加载");
            result.put("hint", "请检查 ConfigServer 是否正常、Git 路径是否正确");
        } else {
            result.put("code", 200);
            result.put("message", "配置加载成功");
            result.put("data", env);
        }

        result.put("timestamp", System.currentTimeMillis());
        return result;
    }

    /**
     * 获取详细配置信息
     */
    @GetMapping("/config")
    public Map<String, Object> config() {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        data.put("env", gitConfig.getEnv()); // ✅ 同样用 getter

        result.put("code", 200);
        result.put("message", "配置详情");
        result.put("data", data);
        result.put("timestamp", System.currentTimeMillis());

        return result;
    }
}
