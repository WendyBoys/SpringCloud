package com.xuan.cloud.controller;

import com.xuan.cloud.entities.CommonResult;
import com.xuan.cloud.entities.Payment;
import com.xuan.cloud.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id") int id)
    {
        return paymentService.getPaymentById(id);
    }


    @GetMapping(value = "/consumer/payment/getTimeoutPort")
    public String getTimeoutPort()
    {

        return paymentService.getTimeoutPort();
    }
}
