package com.seven.second.springcloud.dato;

import com.seven.second.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDto {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
