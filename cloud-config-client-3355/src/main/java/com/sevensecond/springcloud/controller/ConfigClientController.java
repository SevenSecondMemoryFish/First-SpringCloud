package com.sevensecond.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
/** 刷新
 * config service 更改后，还需要手动触发一下，发送一个post请求  http://localhost:3355/actuator/refresh
 * curl -X POST http://localhost:3355/actuator/refresh
 * localhost:3355  标识微服务config-client 的域名
 * */
@RefreshScope
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping(value = "/configInfo")
    public String getConfigInfo() {
        return configInfo;
    }
}
