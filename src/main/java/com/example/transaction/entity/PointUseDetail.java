package com.example.transaction.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 포인트 영역
// 사용 상세: :이번 사용이 어느 적립 건에서 얼마씩 빠졌는가"를 기록하는 연결 테이블
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointUseDetail {
    private Long id;
    private Long pointUseId;
    private Long pointEarnId;
    // 해당 적립 건에서 실제 차감된 양
    private Integer amount;
}
