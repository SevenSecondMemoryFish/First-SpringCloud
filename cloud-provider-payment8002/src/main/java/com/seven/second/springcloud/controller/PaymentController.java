package com.seven.second.springcloud.controller;

import com.seven.second.springcloud.entities.CommentResult;
import com.seven.second.springcloud.entities.Payment;
import com.seven.second.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class  PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String servicePort;

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
}
