package com.xuan.cloud;

import com.xuan.Ribbon.MyRules;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
//@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MyRules.class)
public class ConsumerMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerMainApplication.class,args);
    }
}
