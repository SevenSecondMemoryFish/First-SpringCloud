package com.sevensecond.springcloud.service;

import com.seven.second.springcloud.entities.CommentResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(value = "provider-payment")
public interface OrderNacosService {

    @GetMapping(value = "/nacos/port")
    public CommentResult getPaymentInfo();
}
