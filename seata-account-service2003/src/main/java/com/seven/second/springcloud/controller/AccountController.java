package com.seven.second.springcloud.controller;

import com.seven.second.springcloud.domain.Account;
import com.seven.second.springcloud.domain.CommonResult;
import com.seven.second.springcloud.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
public class AccountController {

    @Resource
    private AccountService accountService;

    @PostMapping(value = "account/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") Integer money) {
        Account account = accountService.findAccountById(userId);
        account.setUsed(account.getUsed() + money);
        account.setResidue(account.getResidue() - money);
        accountService.update(account);
        return new CommonResult(200, "更新成功");
    }
}
