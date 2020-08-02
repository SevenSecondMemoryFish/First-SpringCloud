package com.seven.second.springcloud.service;

import com.seven.second.springcloud.domain.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderService {
    void create(Order order);
}
