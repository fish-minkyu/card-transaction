package com.example.transaction.mapper;

import com.example.transaction.entity.PointWallet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
public class PointWalletMapperTest {
    @Autowired
    PointWalletMapper pointWalletMapper;

    @Test
    void 지갑을_조회한다() {
        PointWallet wallet = pointWalletMapper.findByCustomerId(1L);
        assertThat(wallet.getBalance()).isEqualTo(1800L);
    }

    @Test
    void 포인트를_쓰면_잔액이_줄어든다() {
        PointWallet wallet = pointWalletMapper.findByCustomerId(1L);
        wallet.use(500);
        pointWalletMapper.updateBalance(wallet);

        assertThat(pointWalletMapper.findByCustomerId(1L).getBalance()).isEqualTo(1300);
    }
}
