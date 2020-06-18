package com.seven.second.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentFeignService {
    @Override
    public String paymentInfo_OK(Long id) {
        return "宕机 fall back";
    }

    @Override
    public String paymentInfo_Timeout(Long id) {
        return "宕机 fall back";
    }
}
