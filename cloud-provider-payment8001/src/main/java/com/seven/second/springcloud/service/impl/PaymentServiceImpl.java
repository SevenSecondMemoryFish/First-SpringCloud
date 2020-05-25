package com.seven.second.springcloud.service.impl;

import com.seven.second.springcloud.dato.PaymentDto;
import com.seven.second.springcloud.entities.Payment;
import com.seven.second.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDto paymentDto;

    public int create(Payment payment) {
        return paymentDto.create(payment);
    }

    public Payment getPaymentById(Long id) {
        return paymentDto.getPaymentById(id);
    }
}
