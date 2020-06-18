package com.seven.second.springcloud.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 模拟方法
 */
@Service
public class PaymentService {
    public String paymentInfo_OK(Long id) {
        return "Thread:" + Thread.currentThread().getName() + "id: " + id;
    }

    public String paymentInfo_Timeout(Long id) {
        int number = 3;
        try {
            TimeUnit.SECONDS.sleep(number);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "处理完成，timeout、了";
    }
}
