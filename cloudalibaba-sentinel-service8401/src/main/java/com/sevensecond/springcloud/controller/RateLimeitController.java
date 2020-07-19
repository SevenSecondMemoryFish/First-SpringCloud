package com.sevensecond.springcloud.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.seven.second.springcloud.entities.CommentResult;
import com.seven.second.springcloud.entities.Payment;
import com.sevensecond.springcloud.myhandle.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimeitController {

    @GetMapping("/byResouce")
    @SentinelResource(value = "byResoure", blockHandler = "handleException")
    public CommentResult byResoure() {
        return new CommentResult(200, "按资源名称限流测试成功", new Payment(2020L, "你个大傻子"));
    }

    public CommentResult handleException(BlockException exception) {
        return new CommentResult(404, exception.getClass().getCanonicalName() + "服务不可用");
    }

    @GetMapping("rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommentResult byUrl() {
        return new CommentResult(200, "按url限流测试ok", new Payment(200L, "从入门到放弃"));
    }

    /**
     * blockHandlerClass 处理异常的那个类
     * blockHandler 处理异常类的那个方法，注意：此方法必需是static 方法
     *
     * @return
     */
    @GetMapping("/rateLimit/customer")
    @SentinelResource(value = "customerBlockException",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerBlockException")
    public CommentResult customerBlockException() {
        return new CommentResult(200, "全局限流ok", new Payment(2020L, "从入门到放弃"));
    }

}
