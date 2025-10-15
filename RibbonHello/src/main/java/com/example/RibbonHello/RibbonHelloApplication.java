package com.example.RibbonHello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import com.example.RibbonHello.configuration.RibbonConfig;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//在 Spring Boot 3.x 和较新版本的 Spring Cloud 中，@EnableEurekaClient 注解已经被弃用，不再推荐使用。
//从 Spring Cloud 1.0.0.RC1 版本开始，官方就推荐使用 @EnableDiscoveryClient 来替代 @EnableEurekaClient。
//@EnableEurekaClient
@EnableDiscoveryClient
@RibbonClient(name = "UserService",configuration = RibbonConfig.class)
@EnableFeignClients
public class RibbonHelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(RibbonHelloApplication.class, args);
	}

}
