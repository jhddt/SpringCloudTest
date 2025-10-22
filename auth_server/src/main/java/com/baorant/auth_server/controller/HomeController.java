package com.baorant.auth_server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@Api(tags = "服务认证中心主页")
public class HomeController {

    @ApiOperation("主页")
    @GetMapping("/")
    public String home() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getName())) {
            String authorities = auth.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(", "));
            return "Welcome to OAuth2 Authorization Server!\n" +
                   "Authenticated User: " + auth.getName() + "\n" +
                   "Authorities: " + authorities;
        }
        return "Welcome to OAuth2 Authorization Server!\n" +
               "Please authenticate to access protected resources.";
    }
}
