package com.example.transaction.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 포인트 영역
// 포인트 지갑: 고객당 딱 한 행만 존재하는 잔액 요약 테이블
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointWallet {
    private Long customer_id;
    // 현재 사용가능한 총 포인트
    private Long balance;
    // 낙관적 락용 컬럼
    private Long version;
}
