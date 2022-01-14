package com.bs.productservice.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {
    private final KafkaProducer kafkaProducer;

    @KafkaListener(topics = "order-request")
    public void reduceQty(String message){
        log.info("주문이 요청왔다 해당 상품 재고 삭제해라 productId = "+message);
        // todo - 상품 테이블 재고 확인 후 재거 실패시 error메시지 제공
        kafkaProducer.sendResponseMessageToOrder(message);
    }
}
