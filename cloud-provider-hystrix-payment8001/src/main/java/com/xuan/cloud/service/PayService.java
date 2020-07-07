package com.xuan.cloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j

public class PayService {

    public String ok(int id)
    {
        return Thread.currentThread().getName()+"ok*****"+id;
    }


    @HystrixCommand(fallbackMethod = "timeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "4000")})
    public String timeout(int id) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(1000);
        log.info("8001service");
       // int a=10/0;
        return Thread.currentThread().getName()+"timeout*****"+id;
    }



    public String timeoutHandler(int id)//注意参数 返回值类型 要和上面一样 不然无法识别
    {
        return Thread.currentThread().getName()+"服务器繁忙，请稍后再试!";
    }


}
