package com.seven.second.springcloud.service.impl;

import com.seven.second.springcloud.dao.OrderDao;
import com.seven.second.springcloud.domain.Order;
import com.seven.second.springcloud.service.AccountService;
import com.seven.second.springcloud.service.OrderService;
import com.seven.second.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;


    @Override
    public void create(Order order) {
        orderDao.create(order);
        log.info("开始扣减库存");
//        storageService.decrease(order.getProductId(),order.getCount());
        log.info("开始扣减钱");
//        accountService.decreaseMoney(order.getUserId(),order.getMoney());
        log.info("刷新布局");
        orderDao.update(order.getUserId(), 0);
        log.info("更新完毕");
    }
}
