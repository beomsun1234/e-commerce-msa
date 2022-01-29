package com.bs.orderservice.controller;

import com.bs.orderservice.service.OrderService;
import com.bs.orderservice.service.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("order")
@Slf4j
public class OrderController {
    private final KafkaProducer kafkaProducer;
    private final OrderService orderService;
    @GetMapping("")
    public String test(){
        log.info("order-service");
        return "order-service update";
    }

    @PostMapping("products/{id}/order")
    public String createOrder(@PathVariable Long id){
        log.info("product_id = {} + 주문 요청", id);
        orderService.createOrder(id);
        kafkaProducer.sendProductIdToProduct(String.valueOf(id));
        return id + "주문 성공";
    }

}

