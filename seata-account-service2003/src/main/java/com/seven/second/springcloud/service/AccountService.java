package com.seven.second.springcloud.service;


import com.seven.second.springcloud.domain.Account;
import org.apache.ibatis.annotations.Param;

public interface AccountService {
    Account findAccountById(@Param("userId") Long userId);

    void update(Account account);
}
