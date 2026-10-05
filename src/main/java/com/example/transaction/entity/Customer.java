package com.example.transaction.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 기준정보
// 카드와 포인트 주인
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    private Long id;
    private String name;
    private long balance;

    public void withdraw(long amount) {
        if (amount > balance) {
            throw new IllegalStateException("잔액이 부족합니다.");
        }

        this.balance -= amount;
    }
}
