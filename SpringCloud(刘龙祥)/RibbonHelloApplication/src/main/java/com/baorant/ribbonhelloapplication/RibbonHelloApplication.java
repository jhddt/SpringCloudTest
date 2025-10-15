package com.baorant.ribbonhelloapplication;

import com.baorant.ribbonhelloapplication.config.RibbonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
//@EnableEurekaClient
@EnableDiscoveryClient
@RibbonClient(name = "UserService",configuration = RibbonConfig.class)
public class RibbonHelloApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonHelloApplication.class, args);
    }

}
