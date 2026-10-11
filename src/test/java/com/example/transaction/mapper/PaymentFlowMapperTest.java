package com.example.transaction.mapper;

import com.example.transaction.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PaymentFlowMapperTest {

    @Autowired CardMapper cardMapper;
    @Autowired MerchantMapper merchantMapper;
    @Autowired PaymentMapper paymentMapper;
    @Autowired PointUseMapper pointUseMapper;
    @Autowired PointUseDetailMapper pointUseDetailMapper;

    @Test
    void 카드와_가맹점을_조회한다() {
        Card card = cardMapper.findById(4L);
        Merchant merchant = merchantMapper.findById(2L);

        assertThat(card.getStatus()).isEqualTo("SUSPENDED");
        assertThat(merchant.getFeeRate()).isEqualByComparingTo("0.0150");
    }

    @Test
    void 결제를_저장하고_상태를_바꾼다() {
        Payment payment = Payment.builder()
                .cardId(1L).merchantId(1L).amount(10000).pointUsed(0)
                .status("APPROVED").approvedAt(LocalDateTime.now())
                .build();

        paymentMapper.insert(payment);
        paymentMapper.updateStatus(payment.getId(), "CANCELED");

        assertThat(paymentMapper.findById(payment.getId()).getStatus()).isEqualTo("CANCELED");
    }

    @Test
    void 포인트_사용_이력과_상세를_저장하고_조회한다() {
        Payment payment = Payment.builder()
                .cardId(1L).merchantId(1L).amount(10000).pointUsed(1500)
                .status("APPROVED").approvedAt(LocalDateTime.now())
                .build();
        paymentMapper.insert(payment);

        PointUse use = PointUse.builder()
                .id(1L).paymentId(payment.getId()).amount(1500)
                .type("USE").createdAt(LocalDateTime.now())
                .build();
        pointUseMapper.insert(use);

        // data.sql 기준 고객 1의 적립 건 id: 2(1000p), 3(500p)
        pointUseDetailMapper.insert(PointUseDetail.builder()
                .pointUseId(use.getId()).pointEarnId(2L).amount(1000).build());
        pointUseDetailMapper.insert(PointUseDetail.builder()
                .pointUseId(use.getId()).pointEarnId(3L).amount(500).build());

        PointUse found = pointUseMapper.findByPaymentIdAndType(payment.getId(), "USE");
        List<PointUseDetail> details = pointUseDetailMapper.findByPointUseId(found.getId());

        assertThat(found.getAmount()).isEqualTo(1500);
        assertThat(details).hasSize(2);
        assertThat(details.get(0).getAmount()).isEqualTo(1000);
    }
}
