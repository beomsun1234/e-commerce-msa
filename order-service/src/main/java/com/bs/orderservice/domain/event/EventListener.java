package com.bs.orderservice.domain.event;


import com.bs.orderservice.domain.Order;
import com.bs.orderservice.service.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import javax.persistence.PostPersist;


@Slf4j
@RequiredArgsConstructor
public class EventListener {

    private final KafkaProducer kafkaProducer;

    /**
     * 주문 생성 후 상품 재고 감소 메시지 전달
     * @param order
     */
    @PostPersist
    public void orderPlaced(Order order){
        kafkaProducer.send("order-placed", String.valueOf(order.getProductId()));
    }

}
