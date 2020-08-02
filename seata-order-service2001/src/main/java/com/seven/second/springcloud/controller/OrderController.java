package com.seven.second.springcloud.controller;

import com.seven.second.springcloud.domain.CommonResult;
import com.seven.second.springcloud.domain.Order;
import com.seven.second.springcloud.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @PostMapping(value = "/order/create")
    public CommonResult createOrder(@RequestParam("productId") Long productId, @RequestParam("count") Integer count) {
        Order order = new Order(null, 1L, productId, count, new BigDecimal(12), 0);
        orderService.create(order);
        return new CommonResult(200, "创建成功");
    }
}
