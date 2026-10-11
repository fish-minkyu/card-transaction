package com.example.transaction.mapper;

import com.example.transaction.entity.Merchant;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MerchantMapper {
    Merchant findById(Long id);
}
