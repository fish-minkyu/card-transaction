package com.example.transaction.mapper;

import com.example.transaction.entity.PointEarn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface PointEarnMapper {
    void insert(PointEarn pointEarn);

    List<PointEarn> findUsableByCustomerId(@Param("customerId") Long customerId,
                                           @Param("today") LocalDate today);

    int updateRemaining(PointEarn pointEarn);
}
