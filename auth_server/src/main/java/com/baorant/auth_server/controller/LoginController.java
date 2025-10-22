package com.baorant.auth_server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "认证服务")
public class LoginController {
    @ApiOperation("登录")
    @GetMapping("/login")
    public String login() {
        return "Please use OAuth2 endpoints for authentication:\n" +
               "- Authorization URL: /oauth2/authorize\n" +
               "- Token URL: /oauth2/token\n" +
               "- Use client credentials or authorization code flow";
    }
}
