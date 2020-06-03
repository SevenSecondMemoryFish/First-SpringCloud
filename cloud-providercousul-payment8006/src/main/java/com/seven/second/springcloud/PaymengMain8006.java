package com.seven.second.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PaymengMain8006 {
    public static void main(String[] args) {
        SpringApplication.run(PaymengMain8006.class, args);
    }
}
