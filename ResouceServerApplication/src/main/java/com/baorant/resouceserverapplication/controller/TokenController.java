package com.baorant.resouceserverapplication.controller;

import com.baorant.resouceserverapplication.utils.HttpClientWithBasicAuth;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 令牌控制器类
 * 提供获取OAuth2访问令牌的REST接口
 */
@RestController
@RequestMapping("/token")
public class TokenController {

    private final HttpClientWithBasicAuth httpClient = new HttpClientWithBasicAuth();

    /**
     * 获取访问令牌接口
     * 通过客户端凭证方式向认证服务器请求访问令牌
     * @return 包含令牌信息的响应字符串
     */
    @GetMapping("/get")
    public String getToken() {
        // 准备请求参数
        String url = "http://192.168.141.128:3333/oauth2/token";
        Map<String, String> formData = new HashMap<>();
        formData.put("grant_type", "client_credentials");
        formData.put("scope", "all");
        
        // 发送请求并获取结果
        String result = httpClient.send(url, "test", "123456", formData);
        return "Token Response:\n" + result;
    }
}