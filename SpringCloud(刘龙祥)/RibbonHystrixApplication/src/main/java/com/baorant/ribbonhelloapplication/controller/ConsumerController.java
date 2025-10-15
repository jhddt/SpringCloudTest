package com.baorant.ribbonhelloapplication.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
    @Autowired
    LoadBalancerClient loadBalancerClient;

    /**
     * HystrixCommand默认超时时间为1秒
     */
    @GetMapping("/sayHi")
    @HystrixCommand(fallbackMethod = "sayHiFallback")
    public String sayHi(@RequestParam(value = "sleep_seconds", required = false) Integer sleep_seconds) throws InterruptedException {
        if (sleep_seconds == null) {
            sleep_seconds = 0;
        }
        System.out.println("sayHi begin sleep_seconds: " + sleep_seconds);

        ServiceInstance serviceInstance = loadBalancerClient.choose("UserService");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/user/sayHi?sleep_seconds="+sleep_seconds;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);

        return result;
    }

    public String sayHiFallback(Integer sleep_seconds) {
        System.out.println("sleep_seconds: " + sleep_seconds);
        return "服务User暂时无法响应，请稍候…… ";
    }

    @HystrixCommand(groupKey = "userGroup", commandKey = "sayHi",commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),@HystrixProperty(name = "execution.timeout.enabled", value = "true")},fallbackMethod = "sayHiFallback")
    @RequestMapping("/sayHi1")
    public String sayHi1(@RequestParam(value = "sleep_seconds", required = false) Integer sleep_seconds) throws InterruptedException {
        if (sleep_seconds == null) {
            sleep_seconds = 0;
        }
        System.out.println("sayHi1 begin sleep_seconds: " + sleep_seconds);

        ServiceInstance serviceInstance = loadBalancerClient.choose("UserService");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/user/sayHi?sleep_seconds="+sleep_seconds;

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);

        return result;
    }
}


