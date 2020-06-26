package com.xuan.cloud.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController

public class PaymentController {

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/payment/zk")
    public String getZookeeper()
    {
        return "zookeeper "+port+ UUID.randomUUID().toString();
    }
}
