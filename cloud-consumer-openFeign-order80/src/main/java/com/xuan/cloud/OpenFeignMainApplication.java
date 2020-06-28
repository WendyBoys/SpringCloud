package com.xuan.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OpenFeignMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(OpenFeignMainApplication.class,args);
    }
}
