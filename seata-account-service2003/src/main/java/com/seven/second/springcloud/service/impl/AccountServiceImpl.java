package com.seven.second.springcloud.service.impl;

import com.seven.second.springcloud.dao.AccountDao;
import com.seven.second.springcloud.domain.Account;
import com.seven.second.springcloud.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountDao accountDao;

    @Override
    public Account findAccountById(Long userId) {
        return accountDao.findAccountById(userId);
    }

    @Override
    public void update(Account account) {
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        accountDao.update(account);
    }
}
