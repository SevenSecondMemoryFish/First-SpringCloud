package com.sevensecond.springcloud.controller;

import cn.hutool.core.util.IdUtil;
import com.seven.second.springcloud.entities.CommentResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/nacos/port")
    public CommentResult<Map> getServerPort() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("port", serverPort);
        hashMap.put("uuid", IdUtil.simpleUUID());
        return new CommentResult<Map>(200, "成功", hashMap);
    }
}
