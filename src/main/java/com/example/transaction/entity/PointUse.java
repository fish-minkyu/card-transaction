package com.example.transaction.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 포인트 영역
// 포인트 사용 이력: 포인트가 빠져나간 사건 하나가 한 행
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointUse {
    private Long id;
    // 어느 결제에 썼는지
    private Long payment_id;
    // 이번에 사용된 양
    private Long amount;
    // 사용, 취소 복원, 소멸
    private String type;
}
