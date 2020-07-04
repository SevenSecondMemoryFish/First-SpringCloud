package com.sevensecond.springcloud.controller;

import com.seven.second.springcloud.entities.CommentResult;
import com.sevensecond.springcloud.service.OrderNacosService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class OrderNacosController {
    @Resource
    private OrderNacosService orderNacosService;

    @GetMapping(value = "/consumer/nacos/port")
    public CommentResult<Map> getServerPort() {
        return orderNacosService.getPaymentInfo();
    }
}
