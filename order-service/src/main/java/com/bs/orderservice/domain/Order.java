package com.bs.orderservice.domain;


import com.bs.orderservice.domain.event.EventListener;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "Orders")
@EntityListeners(value = {AuditingEntityListener.class, EventListener.class})
public class Order{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long productId;


    private int quantity;


    private int orderPrice;

    @CreatedDate
    private LocalDateTime createdAt;


    //주문 생성
    @Builder
    public Order(Long id, Long productId,int quantity, int orderPrice){
        this.id = id;
        this.productId = productId;
    }



}
