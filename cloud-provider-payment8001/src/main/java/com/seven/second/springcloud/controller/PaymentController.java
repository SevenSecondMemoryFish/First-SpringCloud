package com.seven.second.springcloud.controller;

import com.seven.second.springcloud.entities.CommentResult;
import com.seven.second.springcloud.entities.Payment;
import com.seven.second.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class  PaymentController {
    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "payment/create")
    public CommentResult create(Payment payment) {
        int result = paymentService.create(payment);
        return new CommentResult(200,"创建成功",result);
    }

    @GetMapping(value = "payment/get/{id}")
    public CommentResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null) {
            return new CommentResult(200,"查询成功",payment);
        } else {
            return new CommentResult(500,"未查询到数据",null);
        }
    }
}
