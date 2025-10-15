package com.baorant.resouceserverapplication.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "Hello from Resource Server!\n" +
               "Authenticated: " + auth.getName() + "\n" +
               "Authorities: " + auth.getAuthorities();
    }
    
    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint, no authentication required.";
    }
}

