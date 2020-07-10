package com.sevensecond.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SentelServerMain8401 {
    public static void main(String[] args) {
        SpringApplication.run(SentelServerMain8401.class, args);
    }
}
