package com.sevensecond.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.seven.second.springcloud.entities.CommentResult;
import com.seven.second.springcloud.entities.Payment;
import com.sevensecond.springcloud.service.OrderNacosService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.InterruptedIOException;

@RestController
public class OrderSentinelController {

    @Resource
    private OrderNacosService nacosService;

    /**
     * fallback 处理系统异常造成的熔断
     * blockHandler 处理sentinel限流的降级，注意：blockHandler 方法参数必需又BlockException，
     * 如果fallback 和blockHandler都配置了，异常处理也达到了sentinel的限流，会走blockHandler的方法调用
     * 注意：两个方法必需和原函数的方法参数一样，blockHandler还需要多一个BlockException参数
     *
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/consumer/fallback/{id}")
    @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandlerFallback")
    public CommentResult testFallback(@PathVariable("id") Long id) throws Exception {
        if (id == 1) {
            throw new InterruptedIOException("也不知道这啥错");
        }
        return nacosService.getPaymentInfo();
    }

    public CommentResult handlerFallback(Long id) {
        return new CommentResult(444, "系统错误了，服务熔断了", null);
    }

    public CommentResult blockHandlerFallback(Long id, BlockException exception) {
        return new CommentResult(445, "服务降级了，放弃吧孩子");
    }
}

