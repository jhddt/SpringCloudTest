package com.baorant.userservice.controller;

import com.baorant.userservice.entity.User;
import com.baorant.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User findByUserName(@RequestParam(value = "userName", required = true) String userName) {
        System.out.println("开始查询...");
        return userService.findUserByName(userName);
    }

    @RequestMapping(value = "/userAll", method = RequestMethod.GET)
    public List<User> findByUserAge() {
        return userService.selectAll();
    }

    @Value("${spring.cloud.client.ip-address}")
    String ipaddr;
    @Value("${server.port}")
    int port;

//    @RequestMapping(value = "/sayHi", method = RequestMethod.GET)
//    public String hello() {
//        return "Hello, 我在" + ipaddr + ":" + port;
//    }

    @RequestMapping(value = "/sayHi", method = RequestMethod.GET)
    public String hello(@RequestParam(value = "sleep_seconds", required = true) Integer sleep_seconds) throws InterruptedException {
        if (sleep_seconds != null) {
            Thread.sleep(sleep_seconds*1000);
        }
        return "Hello, 我在" + ipaddr + ":" + port;
    }
}

