package com.xuan.cloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xuan.cloud.entities.CommonResult;
import com.xuan.cloud.entities.Payment;
import com.xuan.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
//当前controller配置默认的 方法注解只能写 @HystrixCommand 不可带参数 这里可以加参数
@DefaultProperties(defaultFallback = "globalHandler")
public class OrderController {

    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/pay/hystrix/ok")
    public String ok(int id)
    {
        log.info("ok");
        return  paymentService.ok(id);
    }

    @GetMapping(value = "/consumer/pay/hystrix/timeout")
    /*@HystrixCommand(fallbackMethod = "timeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3500")})*/
    @HystrixCommand
    public String timeout(int id) throws InterruptedException {
        log.info("timeout");
       // int a=1/0;
        return  paymentService.timeout(id);
    }

    public String timeoutHandler(int id)//注意参数 返回值类型 要和上面一样 不然无法识别
    {
        return Thread.currentThread().getName()+"客户端繁忙，请稍后再试!";
    }

    public String globalHandler()
    {
        return Thread.currentThread().getName()+"全局服务器繁忙，请稍后再试!";
    }
}


