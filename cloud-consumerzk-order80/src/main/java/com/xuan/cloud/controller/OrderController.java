package com.xuan.cloud.controller;

import com.xuan.cloud.entities.CommonResult;
import com.xuan.cloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/consumer")
@Slf4j
public class OrderController {

    public static final String PAYMENT_URL="http://cloud-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/payment/zk")
    public String get()
    {
        log.info("发起Zookeeper查询请求");
        return restTemplate.getForObject(PAYMENT_URL+"/payment/zk/",String.class);
    }
}
