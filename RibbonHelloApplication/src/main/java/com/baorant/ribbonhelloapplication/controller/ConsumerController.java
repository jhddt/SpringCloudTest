package com.baorant.ribbonhelloapplication.controller;

import com.baorant.ribbonhelloapplication.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 消费者控制器类
 * 提供访问UserService服务的REST接口
 */
@RestController
public class ConsumerController {
    @Autowired
    LoadBalancerClient loadBalancerClient;
    
    /**
     * hello接口方法
     * 通过负载均衡选择UserService实例并构建访问URL
     * @return UserService的hi接口URL地址
     */
    @RequestMapping("/hello")
    public String hello(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("UserService");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/hi";
        System.out.println(url);
        return url;
    }

    /**
     * 获取用户详情接口方法
     * 通过负载均衡选择UserService实例并获取指定用户的信息
     * @param _username 用户名参数
     * @return 用户信息或未找到用户的提示信息
     */
    @RequestMapping("/userdetails")
    public String userdetails(@RequestParam(value = "userName", required = true) String _username) {
        // 通过负载均衡客户端选择UserService实例
        ServiceInstance serviceInstance = loadBalancerClient.choose("UserService");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/user/user?userName="
                + _username;
        System.out.println("url="+url);
        // 创建RestTemplate实例并调用UserService的接口获取用户信息
        RestTemplate restTemplate = new RestTemplate();
        User u = restTemplate.getForObject(url, User.class);
        if (u == null)
            return "没有找到用户记录";
        else
            return u.toString();
    }
}