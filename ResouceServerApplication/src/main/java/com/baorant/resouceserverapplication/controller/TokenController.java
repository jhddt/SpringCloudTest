package com.baorant.resouceserverapplication.controller;

import com.baorant.resouceserverapplication.utils.HttpClientWithBasicAuth;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/token")
public class TokenController {

    private final HttpClientWithBasicAuth httpClient = new HttpClientWithBasicAuth();

    @GetMapping("/get")
    public String getToken() {
        String url = "http://192.168.141.128:3333/oauth2/token";
        Map<String, String> formData = new HashMap<>();
        formData.put("grant_type", "client_credentials");
        formData.put("scope", "all");
        
        String result = httpClient.send(url, "test", "123456", formData);
        return "Token Response:\n" + result;
    }
}
