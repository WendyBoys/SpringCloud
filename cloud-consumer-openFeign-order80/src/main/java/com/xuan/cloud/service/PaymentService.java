package com.xuan.cloud.service;

import com.xuan.cloud.entities.CommonResult;
import com.xuan.cloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("CLOUD-PAYMENT-SERVICE") //指定服务名
public interface PaymentService {

    @GetMapping(value = "/payment/get/{id}") //要和提供者一模一样路径
    CommonResult<Payment> getPaymentById(@PathVariable("id") int id); //提供者返回的是Payment 这里可以加工返回CommonResult

    @GetMapping(value = "/payment/getTimeoutPort") //调用接口时间超过1000ms 应该在ym配置延时超过1000ms 不然会报错
    String getTimeoutPort();
}
