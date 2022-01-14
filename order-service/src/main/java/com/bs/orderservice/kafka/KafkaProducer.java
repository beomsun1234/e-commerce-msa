package com.bs.orderservice.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendTestMessage(String message) {
        kafkaTemplate.send("test", message);
        log.info("Kafka Producer Message : "+ message);
    }

    /**
     * 주문 요청시 재고 감소를 위해
     * @param message
     */
    public void sendProductIdToProduct(String message) {
        kafkaTemplate.send("order-request", message);
        log.info("상품 id = "+ message);
    }


}
