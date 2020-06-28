package com.xuan.cloud.service;

import com.xuan.cloud.entities.CommonResult;
import com.xuan.cloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("CLOUD-PAYMENT-SERVICE")
public interface PaymentService {

    @GetMapping(value = "/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") int id);

    @GetMapping(value = "/payment/getTimeoutPort")
    String getTimeoutPort();
}
