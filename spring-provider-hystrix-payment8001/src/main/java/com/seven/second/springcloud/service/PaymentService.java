package com.seven.second.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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


    /**-----------服务熔断-----------*/
    /**
     * 1.开启熔断服务
     * 2。在窗口期内，请求次数超过10次，或者失败的百分比大于60讲开启服务熔断
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),// 开启熔断
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),/// 窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")///失败百分比
    })
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("****** id****不可以为负数");
        }

        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "调用成功，流水号：" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(Integer id) {
        return "服务熔断，开启了，你自己开着办吧";
    }
}
