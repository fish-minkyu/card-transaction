package com.example.transaction.mapper;

import com.example.transaction.entity.PointWallet;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PointWalletMapper {
    PointWallet findByCustomerId(Long customerId);
    PointWallet findByCustomerIdForUpdate(Long customerId);
    int updateBalance(PointWallet wallet);
}
