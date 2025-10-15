package com.baorant.auth_server.controller;

import com.baorant.auth_server.entity.SpringcloudUser;
import com.baorant.auth_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserService userService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/db")
    public String testDatabase() {
        try {
            SpringcloudUser user = userService.getUser("admin");
            if (user != null) {
                return "Database connection successful! Found user: " + user.getUsername();
            } else {
                return "Database connection successful, but no admin user found.";
            }
        } catch (Exception e) {
            return "Database connection failed: " + e.getMessage();
        }
    }

    @GetMapping("/oauth-clients")
    public List<Map<String, Object>> testOauthClients() {
        try {
            return jdbcTemplate.queryForList("SELECT * FROM oauth_client_details");
        } catch (Exception e) {
            return List.of(Map.of("error", "Failed to query oauth_client_details: " + e.getMessage()));
        }
    }
}
