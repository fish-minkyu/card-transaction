package com.example.transaction.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// 정산 영역
// 가맹점 일별 정산: 정산 배치의 결과물
// merchant_id와 settle_date의 조합이 어느 가맹점의 며칠치 정산인지를 나타낸다.
// 또한, 이 두 컬럼에 유니크 제약을 걸어 배치가 재실행돼도 중복 정산이 생기지 않도록 막는다.
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Settlement {
    private Long id;
    private Long merchantId;
    private LocalDate settleDate;
    // 그 날 승인 금액 합계(취소분 제외)
    private Long totalAmount;
    // total_amount × fee_rate로 계산한 수수료
    private Long fee;
    // 실제 가맹점에 지급할 금액
    private Long netAmount;
}
