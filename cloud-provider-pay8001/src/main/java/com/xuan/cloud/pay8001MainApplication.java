package com.xuan.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class pay8001MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(pay8001MainApplication.class,args);
    }
}
