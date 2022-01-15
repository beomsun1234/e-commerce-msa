package com.bs.productservice.kafka;

import com.bs.productservice.domain.Product;
import com.bs.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {
    private final KafkaProducer kafkaProducer;
    private final ProductRepository productRepository;
    @KafkaListener(topics = "order-request")
    public void reduceQty(String message){
        log.info("주문이 요청왔다 해당 상품 재고 삭제해라 productId = "+message);

        Product product = productRepository.findById(Long.valueOf(message))
                .orElseThrow();

        product.reduceQuantity();
        productRepository.save(product);
        kafkaProducer.sendResponseMessageToOrder(message);

    }
}
