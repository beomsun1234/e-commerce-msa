package com.bs.orderservice.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {

    @KafkaListener(topics = "test")
    public void consume(String message){
        log.info("Kafka Consumer: " + message);
    }

    @KafkaListener(topics = "product-qty")
    public void completeOrder(String message){
        log.info("productId = "+message+"의 재고가 감소 됐습니다. 주문이 정상적으로 성사됐습니다.");
        // todo - 재고가 성공적으로 감소시 주문테이블에 주문상태 변경
    }

}
