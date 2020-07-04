package com.xuan.cloud.service;

import com.xuan.cloud.entities.CommonResult;
import com.xuan.cloud.entities.Payment;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.TimeUnit;

@Component
@FeignClient("cloud-payment-hystrix-service") //指定服务名
public interface PaymentService {

    @GetMapping(value = "/pay/hystrix/ok")
    String ok(@RequestParam("id") int id);


    @GetMapping(value = "/pay/hystrix/timeout")
    String timeout(@RequestParam("id")int id);
}
