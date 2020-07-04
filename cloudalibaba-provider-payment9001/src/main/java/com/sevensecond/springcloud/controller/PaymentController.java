package com.sevensecond.springcloud.controller;

import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    public String getServerPort() {
        return "server port: " + serverPort + "uuid: " + IdUtil.simpleUUID();
    }
}
