package com.example.transaction.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 기준정보
// 가맹짐: 정산을 받는 주체
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Merchant {
    private Long id;
    private String name;
    // 수수료율
    private Float fee_rate;
}
