package com.baorant.apigateway.config;

import com.baorant.apigateway.filter.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {
    @Bean
    public MyPreFilter preFilter() {
        return new MyPreFilter();
    }

    @Bean
    public MyPreFilter2 preFilter2() {
        return new MyPreFilter2();
    }

    @Bean
    public MyRoutingFilter myRoutingFilter() {
        return new MyRoutingFilter();
    }

    @Bean
    public MyRoutingFilter2 myRoutingFilter2() {
        return new MyRoutingFilter2();
    }

    @Bean
    public MyErrorFilter myErrorFilter() {
        return new MyErrorFilter();
    }

    @Bean
    public MyPostFilter myPostFilter() {
        return new MyPostFilter();
    }

    @Bean
    public IPFilter iPFilter() {
        return new IPFilter();
    }

    @Bean
    public LogFilter logFilter() {
        return new LogFilter();
    }
}
