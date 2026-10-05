-- 고객 3명
INSERT INTO customer (id, name) VALUES
                                    (1, '김민수'),
                                    (2, '이지은'),
                                    (3, '박서준');

-- 가맹점 3곳 (수수료율 다르게)
INSERT INTO merchant (id, name, fee_rate) VALUES
                                              (1, '동네 편의점', 0.0080),
                                              (2, '대형 마트',   0.0150),
                                              (3, '온라인 쇼핑', 0.0230);

-- 카드 (가짜 번호, 4번 카드는 정지 상태 → 결제 거절 테스트용)
INSERT INTO card (id, customer_id, card_number, status) VALUES
                                                            (1, 1, '9410-0000-0000-0001', 'ACTIVE'),
                                                            (2, 1, '9410-0000-0000-0002', 'ACTIVE'),
                                                            (3, 2, '9410-0000-0000-0003', 'ACTIVE'),
                                                            (4, 3, '9410-0000-0000-0004', 'SUSPENDED');

-- 미리 쌓여 있는 이벤트 포인트 (결제 없이 적립 → payment_id NULL)
-- 고객 1: 유효기간이 서로 다른 3건 (하나는 이미 만료 → 소멸 배치 테스트용)
INSERT INTO point_earn (customer_id, payment_id, amount, remaining, expire_at, status) VALUES
                                                                                           (1, NULL, 300,  300,  '2026-09-30', 'ACTIVE'),
                                                                                           (1, NULL, 1000, 1000, '2026-10-31', 'ACTIVE'),
                                                                                           (1, NULL, 500,  500,  '2026-12-31', 'ACTIVE'),
                                                                                           (2, NULL, 2000, 2000, '2027-03-31', 'ACTIVE');

-- 지갑 잔액 = 해당 고객 적립 건 remaining 합계와 일치해야 함
INSERT INTO point_wallet (customer_id, balance, version) VALUES
                                                             (1, 1800, 0),
                                                             (2, 2000, 0),
                                                             (3, 0,    0);