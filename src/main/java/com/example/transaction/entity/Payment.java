package com.example.transaction.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// 결제 영역
// 결제 승인 내역: 포인트 적입, 사용과 정산이 모두 이 테이블에서 파생된다.
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    private Long id;
    // 누가 결제했는지
    private Long cardId;
    // 어느 가맹점에서 결제했는지
    private Long merchantId;
    // 결제 총액
    private Long amount;
    // 포인트로 낸 금액
    private Long pointUsed;
    // 승인, 취소
    private String status;
    private LocalDateTime approvedAt;
}
