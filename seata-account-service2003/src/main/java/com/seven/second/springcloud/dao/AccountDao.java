package com.seven.second.springcloud.dao;

import com.seven.second.springcloud.domain.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountDao {
    void update(Account account);

    Account findAccountById(@Param("userId") Long userId);
}
