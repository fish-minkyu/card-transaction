package com.example.transaction.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// 포인트 영역
// 포인트 적립 건: 적힙 한 번이 한 행
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointEarn {
    private Long id;
    private Long customer_id;
    private Long payment_id;
    // 최초 적립량
    private Long amount;
    // 현재 남은 양
    private Long remaining;
    // 포인트 만료일자
    private LocalDate expire_at;
    // 사용 가능, 전부 사용, 소멸, 회수
    private String status;
}
