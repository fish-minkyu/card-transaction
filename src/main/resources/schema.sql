-- 실행할 때마다 테이블을 지우고 새로 만듭니다 (FK 때문에 자식 테이블부터 삭제)
DROP TABLE IF EXISTS settlement;
DROP TABLE IF EXISTS point_use_detail;
DROP TABLE IF EXISTS point_use;
DROP TABLE IF EXISTS point_earn;
DROP TABLE IF EXISTS payment;
DROP TABLE IF EXISTS point_wallet;
DROP TABLE IF EXISTS card;
DROP TABLE IF EXISTS merchant;
DROP TABLE IF EXISTS customer;

-- 고객
CREATE TABLE customer (
                          id   BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(50) NOT NULL
);

-- 가맹점
CREATE TABLE merchant (
                          id       BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name     VARCHAR(100) NOT NULL,
                          fee_rate DECIMAL(5, 4) NOT NULL          -- 예: 0.0150 = 1.5%
);

-- 카드
CREATE TABLE card (
                      id          BIGINT AUTO_INCREMENT PRIMARY KEY,
                      customer_id BIGINT      NOT NULL,
                      card_number VARCHAR(19) NOT NULL,
                      status      VARCHAR(20) NOT NULL,        -- ACTIVE, SUSPENDED, CLOSED
                      CONSTRAINT fk_card_customer FOREIGN KEY (customer_id) REFERENCES customer (id)
);

-- 포인트 지갑 (고객당 1행)
CREATE TABLE point_wallet (
                              customer_id BIGINT PRIMARY KEY,
                              balance     BIGINT NOT NULL DEFAULT 0,
                              version     BIGINT NOT NULL DEFAULT 0,
                              CONSTRAINT fk_wallet_customer FOREIGN KEY (customer_id) REFERENCES customer (id)
);

-- 결제 승인 내역
CREATE TABLE payment (
                         id          BIGINT AUTO_INCREMENT PRIMARY KEY,
                         card_id     BIGINT      NOT NULL,
                         merchant_id BIGINT      NOT NULL,
                         amount      BIGINT      NOT NULL,
                         point_used  BIGINT      NOT NULL DEFAULT 0,
                         status      VARCHAR(20) NOT NULL,        -- APPROVED, CANCELED
                         approved_at DATETIME    NOT NULL,
                         CONSTRAINT fk_payment_card     FOREIGN KEY (card_id)     REFERENCES card (id),
                         CONSTRAINT fk_payment_merchant FOREIGN KEY (merchant_id) REFERENCES merchant (id)
);
CREATE INDEX idx_payment_approved_at ON payment (approved_at);

-- 포인트 적립 건
CREATE TABLE point_earn (
                            id          BIGINT AUTO_INCREMENT PRIMARY KEY,
                            customer_id BIGINT      NOT NULL,
                            payment_id  BIGINT,                      -- 이벤트 적립이면 NULL
                            amount      BIGINT      NOT NULL,
                            remaining   BIGINT      NOT NULL,
                            expire_at   DATE        NOT NULL,
                            status      VARCHAR(20) NOT NULL,        -- ACTIVE, USED_UP, EXPIRED, REVOKED
                            CONSTRAINT fk_earn_customer FOREIGN KEY (customer_id) REFERENCES customer (id),
                            CONSTRAINT fk_earn_payment  FOREIGN KEY (payment_id)  REFERENCES payment (id)
);
CREATE INDEX idx_earn_customer_expire ON point_earn (customer_id, expire_at);

-- 포인트 사용 이력
CREATE TABLE point_use (
                           id          BIGINT AUTO_INCREMENT PRIMARY KEY,
                           customer_id BIGINT      NOT NULL,
                           payment_id  BIGINT,                      -- 소멸이면 NULL
                           amount      BIGINT      NOT NULL,
                           type        VARCHAR(20) NOT NULL,        -- USE, RESTORE, EXPIRE
                           created_at  DATETIME    NOT NULL,
                           CONSTRAINT fk_use_customer FOREIGN KEY (customer_id) REFERENCES customer (id),
                           CONSTRAINT fk_use_payment  FOREIGN KEY (payment_id)  REFERENCES payment (id)
);

-- 포인트 사용 상세 (어느 적립 건에서 얼마 빠졌는지)
CREATE TABLE point_use_detail (
                                  id            BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  point_use_id  BIGINT NOT NULL,
                                  point_earn_id BIGINT NOT NULL,
                                  amount        BIGINT NOT NULL,
                                  CONSTRAINT fk_detail_use  FOREIGN KEY (point_use_id)  REFERENCES point_use (id),
                                  CONSTRAINT fk_detail_earn FOREIGN KEY (point_earn_id) REFERENCES point_earn (id)
);

-- 가맹점 일별 정산
CREATE TABLE settlement (
                            id           BIGINT AUTO_INCREMENT PRIMARY KEY,
                            merchant_id  BIGINT NOT NULL,
                            settle_date  DATE   NOT NULL,
                            total_amount BIGINT NOT NULL,
                            fee          BIGINT NOT NULL,
                            net_amount   BIGINT NOT NULL,
                            CONSTRAINT fk_settlement_merchant FOREIGN KEY (merchant_id) REFERENCES merchant (id),
                            CONSTRAINT uk_settlement UNIQUE (merchant_id, settle_date)
);