package com.baorant.auth_server.controller;

import com.baorant.auth_server.entity.SpringcloudUser;
import com.baorant.auth_server.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 认证服务测试控制器
 * 提供数据库连接测试和OAuth客户端查询功能
 */
@RestController
@RequestMapping("/test")
@Api(tags = "认证服务测试")
public class TestController {
    @Autowired
    private UserService userService;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     * 测试数据库连接状态
     * 通过查询admin用户来验证数据库连接是否正常
     * 
     * @return String 数据库连接测试结果信息
     */
    @ApiOperation("测试数据库连接")
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

    /**
     * 查询OAuth客户端信息
     * 从oauth_client_details表中获取所有客户端详情
     * 
     * @return List<Map<String, Object>> OAuth客户端信息列表
     */
    @ApiOperation("查询OAuth客户端信息")
    @GetMapping("/oauth-clients")
    public List<Map<String, Object>> testOauthClients() {
        try {
            return jdbcTemplate.queryForList("SELECT * FROM oauth_client_details");
        } catch (Exception e) {
            return List.of(Map.of("error", "Failed to query oauth_client_details: " + e.getMessage()));
        }
    }
}