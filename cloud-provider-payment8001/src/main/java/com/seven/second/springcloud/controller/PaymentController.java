package com.seven.second.springcloud.controller;

import com.seven.second.springcloud.entities.CommentResult;
import com.seven.second.springcloud.entities.Payment;
import com.seven.second.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class  PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String servicePort;

    @Value("${spring.application.name}")
    private String serviceName;

    /** 服务发现*/
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "payment/create")
    public CommentResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        return new CommentResult(200,"创建成功",result);
    }

    @GetMapping(value = "payment/get/{id}")
    public CommentResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null) {
            return new CommentResult(200,"查询成功, port: " + servicePort,payment);
        } else {
            return new CommentResult(500,"未查询到数据",null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public CommentResult discovery() {

        /// 查询注册中心服务提供者
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("service: " + service);
        }

        /// 根据serviceId查询服务提供者信息
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName.toUpperCase());
        List list = new ArrayList<Map>();
        HashMap map = null;
        for (ServiceInstance instance : instances) {
            map = new HashMap();
            map.put("name", instance.getInstanceId());
            map.put("host", instance.getHost());
            map.put("port",instance.getPort());
            map.put("url",instance.getUri());
            list.add(map);
        }
        return new CommentResult(200,"查询成功",list);
    }
}