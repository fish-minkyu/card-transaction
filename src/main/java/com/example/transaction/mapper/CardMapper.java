package com.example.transaction.mapper;

import com.example.transaction.entity.Card;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CardMapper {
    Card findById(Long id);
}
