package com.example.transaction.mapper;

import com.example.transaction.entity.PointUseDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PointUseDetailMapper {
    void insert(PointUseDetail detail);

    // 결제 취소 시 어느 적립 건에 얼마를 돌려줄지 찾기 위함
    List<PointUseDetail> findByPointUseId(Long pointUseId);
}
