package com.baorant.configclient.controller;

import com.baorant.configclient.config.GitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/test")
public class TestController {
    @Autowired
    GitConfig gitConfig;

    @GetMapping("/show")
    public String show() {
        return "Current Environment: " + gitConfig.env;
    }
    
    @GetMapping("/health")
    public String health() {
        return "ConfigClient is running";
    }
    
    @GetMapping("/config")
    public Map<String, String> config() {
        Map<String, String> configMap = new HashMap<>();
        configMap.put("env", gitConfig.env);
        return configMap;
    }
}