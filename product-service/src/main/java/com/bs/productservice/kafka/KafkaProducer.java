package com.bs.productservice.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class KafkaProducer {
    private final KafkaTemplate<String,String> kafkaTemplate;

    /**
     * 재고수량 감소
     * @param message
     */
    public void sendResponseMessageToOrder(String message){
        kafkaTemplate.send("product-qty",message);
    }


}
