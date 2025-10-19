package com.example.UserService.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.example.UserService.entity.User;
import com.example.UserService.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public boolean addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public boolean updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public boolean delete(@RequestParam(value = "userName", required = true) String _username) {
        return userService.deleteUser(_username);
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public User findByUserName(@RequestParam(value = "userName", required = true) String userName) {
        System.out.println("开始查询...");
        return userService.findUserByName(userName);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<User> selectAll() {
        return userService.selectAll();
    }


    @Value("${spring.cloud.client.ip-address}")
    String ipaddr;
    @Value("${server.port}")
    int port;
//    @RequestMapping(value = "/sayHi", method = RequestMethod.GET)
//    public String hello(@RequestParam(value = "sleep_seconds", required = true) int sleep_seconds) throws InterruptedException {
//        Thread.sleep(sleep_seconds * 1000); //休眠
//        return "Hello, 我在" + ipaddr + ":" + port;
//    }

    @GetMapping("/sayHi")
    public String sayHi(@RequestParam(value = "sleep_seconds", required = true) int sleep_seconds) {
        System.out.println("sayHi begin sleep_seconds: " + sleep_seconds);
        try {
            Thread.sleep(sleep_seconds * 10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "👋 Hello from UserService! 我在:" + ipaddr + ":" + port + " (sleep=" + sleep_seconds + "s)";
    }

}
