package com.baorant.configclient.controller;

import com.baorant.configclient.config.GitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
