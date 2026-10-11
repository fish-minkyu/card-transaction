package com.example.transaction.mapper;

import com.example.transaction.entity.PointUse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PointUseMapper {
    void insert(PointUse pointUse);

    // 결제 취소 시 "이 결제에서 쓴 포인트"를 찾기 위함
    PointUse findByPaymentIdAndType(@Param("paymentId") Long paymentId,
                                    @Param("type") String type);
}
