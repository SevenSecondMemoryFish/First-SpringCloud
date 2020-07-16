package com.sevensecond.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * SentinelResource value 设置的热点key， 但达到阀值的时候会调用blockHandler 方法
     * 热点限流是填写的参数的索引，如填写的是0，标识对p1进行限流，当达到阀值的时候会调用blockHandler
     * 当使用热点限流里面的参数例外项的时候
     * 标识当 参数值是填写的例外项值相等的时候，会是一个新的阀值
     *
     * @param p1
     * @param p2
     * @return
     */
    @GetMapping("/testHotkey")
    @SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        return "*****  testHotKey *****";
    }

    public String deal_testHotKey(String p1, String p2, BlockException exception) {
        return "testHotKey 被限流，失败了";
    }
}
