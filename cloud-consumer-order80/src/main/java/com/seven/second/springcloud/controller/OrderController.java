package com.seven.second.springcloud.controller;

import com.seven.second.springcloud.entities.CommentResult;
import com.seven.second.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {
    /** 单机版 */
    // public static final String PAYMENT_URL = "http://localhost:8001";
    /**  集群写法，服务名称*/
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommentResult<Payment> create(Payment payment) {
        log.info("我试测试");
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommentResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommentResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommentResult.class);
    }

    @GetMapping("/consumer/entity/get/{id}")
    public CommentResult<Payment> getPaymentEntity(@PathVariable("id") Long id) {
        /// entity 可以获取到更多响应数据，比如header statuscode等
        ResponseEntity<CommentResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommentResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new CommentResult<>(400, "未查询到数据");
        }
    }
}
