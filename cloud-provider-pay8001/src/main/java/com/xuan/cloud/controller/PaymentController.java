package com.xuan.cloud.controller;



import com.xuan.cloud.entities.CommonResult;
import com.xuan.cloud.entities.Payment;
import com.xuan.cloud.service.PaymentService;
import io.micrometer.core.instrument.util.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("******插入结果"+result);
        if(result>0)
        {
            return new CommonResult(200,"插入数据库成功,端口为"+port,payment.getId());
        }
        else
        {
            return new CommonResult(444,"插入数据库失败,端口为"+port,null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult get(@PathVariable("id")Integer id){
        Payment payMentById = paymentService.getPaymentById(id);
        log.info("******查询结果"+payMentById);
        if(payMentById!=null)
        {
            return new CommonResult(200,"查询成功,端口为"+port,payMentById);
        }
        else
        {
            return new CommonResult(444,"没有对应记录，查询失败,端口为"+port,null);
        }
    }

    //获取服务信息
    @GetMapping(value = "/payment/discovery")
    public Object discovery()
    {
        //获取应用名称
        List<String> services = discoveryClient.getServices();
        for (String service:services)
        {
            log.info("*****"+service);
        }
        //根据名称获取详细信息
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for(ServiceInstance serviceInstance:instances)
        {
            log.info("*****"+serviceInstance.getServiceId()+" "+serviceInstance.getHost()+" " +serviceInstance.getPort()+" "+serviceInstance.getUri());
        }

        return discoveryClient;
    }

    @GetMapping(value = "/payment/getPort")
    public String getPort()
    {
        return port;
    }

    @GetMapping(value = "/payment/getTimeoutPort")
    public String getTimeoutPort()
    {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return port;
    }
}
