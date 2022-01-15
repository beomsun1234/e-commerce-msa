package com.bs.orderservice.service;

import com.bs.orderservice.domain.Order;
import com.bs.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public void createOrder(Long productId){
        orderRepository.save(Order.builder().productId(productId).build());
        log.info("주문 완료");
    }

}
