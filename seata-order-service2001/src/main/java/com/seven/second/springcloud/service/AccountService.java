package com.seven.second.springcloud.service;

import com.seven.second.springcloud.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Component
@FeignClient(value = "seata-account-service")
public interface AccountService {

    @PostMapping(value = "/account/decrease")
    public CommonResult decreaseMoney(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
