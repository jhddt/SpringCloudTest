package com.baorant.resouceserverapplication.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * 测试控制器类
 * 提供用于测试资源服务器认证和授权功能的REST接口
 */
@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * 受保护的Hello接口
     * 需要有效的JWT令牌才能访问，返回当前认证用户的信息
     * @return 包含认证用户信息的字符串
     */
    @GetMapping("/hello")
    public String hello() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "Hello from Resource Server!\n" +
               "Authenticated: " + auth.getName() + "\n" +
               "Authorities: " + auth.getAuthorities();
    }
    
    /**
     * 公共接口
     * 无需认证即可访问的公共端点
     * @return 公共信息字符串
     */
    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint, no authentication required.";
    }
}