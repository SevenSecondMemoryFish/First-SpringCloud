package com.seven.second.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHander", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_Timeout(Long id) {
        int number = 5;
        try {
            TimeUnit.SECONDS.sleep(number);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "处理完成，timeout、了";
    }

    public String paymentInfo_TimeoutHander(Long id) {
        return "服务繁忙，自己想办法吧";
    }

}
