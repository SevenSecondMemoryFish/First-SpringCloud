package com.seven.second.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.seven.second.springcloud.service.PaymentFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class OrderHystrixController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Long id) {
        return paymentFeignService.paymentInfo_OK(id);
    }

    /**
     * 个别处理fallback方法
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHander", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })
    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id") Long id) {
        return paymentFeignService.paymentInfo_Timeout(id);
    }

    @HystrixCommand
    @GetMapping(value = "/consumer/payment/hystrix/fallback/{id}")
    public String paymentInfo_DefaulFallback(@PathVariable("id") Long id) {
        List list = new ArrayList();
        list.get(0);
        return  paymentFeignService.paymentInfo_Timeout(id);
    }

    public String paymentInfo_TimeoutHander(Long id) {
        return "我是80 order 服务。系统出错了。没办法了，自己看着办吧";
    }

    public String defaultFallback() {
        return "我是全局的降级方法，你自己看着办班";
    }
}
