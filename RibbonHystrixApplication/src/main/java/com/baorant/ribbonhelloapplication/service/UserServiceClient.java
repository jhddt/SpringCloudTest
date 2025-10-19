package com.baorant.ribbonhelloapplication.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * 用户服务客户端类，用于通过负载均衡调用用户服务接口
 */
@Service
public class UserServiceClient {

    private final LoadBalancerClient loadBalancerClient;
    private final WebClient webClient;

    /**
     * 构造函数，初始化负载均衡客户端和WebClient
     *
     * @param loadBalancerClient 负载均衡客户端，用于选择服务实例
     */
    public UserServiceClient(LoadBalancerClient loadBalancerClient) {
        this.loadBalancerClient = loadBalancerClient;
        this.webClient = WebClient.builder().build();
    }

    /**
     * 调用用户服务的sayHi接口，并应用熔断机制
     *
     * @param sleepSeconds 睡眠秒数，用于模拟服务延迟
     * @return 用户服务返回的响应字符串
     */
    @CircuitBreaker(name = "userServiceCircuitBreaker", fallbackMethod = "fallbackSayHi")
    public String sayHi(int sleepSeconds) {
        // 负载均衡选择实例
        ServiceInstance instance = loadBalancerClient.choose("UserService");
        if (instance == null) {
            throw new RuntimeException("没有可用的 UserService 实例");
        }

        String url = "http://" + instance.getHost() + ":" + instance.getPort()
                + "/user/sayHi?sleep_seconds=" + sleepSeconds;

        System.out.println("调用 URL: " + url);

        return webClient.get()
                .uri(url)
                .retrieve()
                .onStatus(status -> status.isError(),
                        response -> Mono.error(new RuntimeException("UserService 返回错误: " + response.statusCode())))
                .bodyToMono(String.class)
                // 设置 5 秒超时
                .timeout(Duration.ofSeconds(5))
                .block();
    }

    /**
     * 熔断回调方法，当服务调用失败或超时时执行
     *
     * @param sleepSeconds 睡眠秒数参数
     * @param throwable 异常信息
     * @return 熔断后的回退响应
     */
    // 熔断回退方法
    public String fallbackSayHi(int sleepSeconds, Throwable throwable) {
        System.out.println("触发熔断，原因: " + throwable.getMessage());
        return "UserService 超时或失败，已降级处理";
    }
}