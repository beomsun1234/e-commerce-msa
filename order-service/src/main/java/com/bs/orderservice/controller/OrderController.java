package com.bs.orderservice.controller;

import com.bs.orderservice.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("order")
@Slf4j
public class OrderController {
    private final KafkaProducer kafkaProducer;
    @GetMapping("")
    public String test(){
        log.info("order-service");
        return "order-service";
    }

    @PostMapping("products/{id}/order")
    public String createOrder(@PathVariable String id){
        log.info("product_id = {} + 주문 요청", id);
        kafkaProducer.sendProductIdToProduct(id);
        return id + "주문 성공";
    }

}
