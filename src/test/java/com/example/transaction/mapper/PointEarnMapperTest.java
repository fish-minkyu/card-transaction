package com.example.transaction.mapper;

import com.example.transaction.entity.PointEarn;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class PointEarnMapperTest {
    @Autowired
    PointEarnMapper pointEarnMapper;

    @Test
    void 사용_가능한_포인트를_만료_임박순으로_조회한다() {
        LocalDate today = LocalDate.of(2026, 10, 5);

        List<PointEarn> result = pointEarnMapper.findUsableByCustomerId(1L, today);

        assertThat(result).hasSize(2);   // 9/30 만료분은 제외
        assertThat(result.get(0).getRemaining()).isEqualTo(1000);  // 10/31 만료가 먼저
        assertThat(result.get(1).getRemaining()).isEqualTo(500);   // 12/31 만료가 다음
    }

    @Test
    void 저장하면_id가_채워진다() {
        PointEarn earn = PointEarn.builder()
            .customerId(3L)
            .amount(100)
            .remaining(100)
            .expireAt(LocalDate.of(2027, 1, 1))
            .status("ACTIVE")
            .build();

        pointEarnMapper.insert(earn);

        assertThat(earn.getId()).isNotNull();
    }
}
