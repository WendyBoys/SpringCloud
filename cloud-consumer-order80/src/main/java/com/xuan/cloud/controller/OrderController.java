package com.xuan.cloud.controller;

import com.xuan.cloud.entities.CommonResult;
import com.xuan.cloud.entities.Payment;
import com.xuan.cloud.loadBalancer.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/consumer")
@Slf4j
public class OrderController {

    public static final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private LoadBalancer loadBalancer;

    @GetMapping(value = "/payment/create")
    public CommonResult<Payment> create(Payment payment)
    {
        log.info("发起Post创建请求");
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id") int id)
    {
        log.info("发起Get查询请求");
        //getObject 基本返回的都是json
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    @GetMapping(value = "/payment/getEntity/{id}")
    public CommonResult<Payment> getEntity(@PathVariable("id") int id)
    {
        log.info("发起GetEntity查询请求");
        //getEntity返回ResponseEntuty对象 包含了响应信息，比如状态码，响应头，响应体
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful())
        {
            return entity.getBody();
        }else
        {
            return new CommonResult(444,"访问失败");
        }
    }


    @GetMapping(value = "/payment/LoadBalancer")
    public String LoadBalacer()
    {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        ServiceInstance serviceInstance = loadBalancer.serviceInstance(instances);
        URI uri = serviceInstance.getUri();
        String port = restTemplate.getForObject(uri + "payment/getPort", String.class);
        return port;
    }
}
