package com.example.transaction.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 기준정보
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card {
    private Long id;
    // 카드의 주인
    private Long custmer_id;
    private String card_number;
    // 정상, 정지, 해지
    private String status;
}
