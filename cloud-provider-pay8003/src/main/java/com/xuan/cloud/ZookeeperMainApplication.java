package com.xuan.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ZookeeperMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZookeeperMainApplication.class,args);
    }
}
