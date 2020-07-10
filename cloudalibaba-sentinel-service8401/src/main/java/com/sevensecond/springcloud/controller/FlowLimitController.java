package com.sevensecond.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        return "----textA---";
    }

    @GetMapping("/testB")
    public String testB() {
        return "----textB---";
    }
}
