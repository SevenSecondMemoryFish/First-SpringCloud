package com.seven.second.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
/**
 * 日志级别
 * 1.NONE： 默认的，不显示任何日志
 * 2.BASIC: 仅记录请求方法，URL，响应状态码及执行的时间
 * 3.HEADERS   除了BASIC,中定义的信息之外，还有请求和响应的头信息
 * 4.FULL      除了HEADERS中定义的信息之外，还有请求和响应的正文及元数据
 */
