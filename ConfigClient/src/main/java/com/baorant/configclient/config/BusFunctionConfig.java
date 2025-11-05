package com.baorant.configclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.util.function.Consumer;

@Component
public class BusFunctionConfig {

    @Bean
    public Consumer<String> busConsumer() {
        return msg -> System.out.println("Bus event received: " + msg);
    }
}

