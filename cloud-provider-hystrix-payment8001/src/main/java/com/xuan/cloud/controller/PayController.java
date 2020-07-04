package com.xuan.cloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xuan.cloud.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PayController {

    @Resource
    private PayService payService;

    @GetMapping(value = "/pay/hystrix/ok")
    public String ok( int id)
    {
        log.info("ok");
       return  payService.ok(id);

    }

    @GetMapping(value = "/pay/hystrix/timeout")
    public String timeout(int id) throws InterruptedException {
        log.info("timeout");
        return  payService.timeout(id);
    }


}
