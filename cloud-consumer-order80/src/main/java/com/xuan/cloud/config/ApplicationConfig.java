package com.xuan.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {

    @Bean
    @LoadBalanced //内部使用了Ribbon实现了客户端的负载均衡
    public RestTemplate getRestTemplate()
    {
        return new RestTemplate();
    }
}
