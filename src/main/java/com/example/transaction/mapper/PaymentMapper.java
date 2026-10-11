package com.example.transaction.mapper;

import com.example.transaction.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentMapper {
    void insert(Payment payment);

    Payment findById(Long id);

    int updateStatus(@Param("id") Long id, @Param("status") String status);
}
