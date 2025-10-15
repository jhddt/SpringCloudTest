package com.baorant.auth_server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "Please use OAuth2 endpoints for authentication:\n" +
               "- Authorization URL: /oauth2/authorize\n" +
               "- Token URL: /oauth2/token\n" +
               "- Use client credentials or authorization code flow";
    }
}
