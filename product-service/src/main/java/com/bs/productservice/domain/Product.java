package com.bs.productservice.domain;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.time.LocalDateTime;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "Products")
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int stock_quantity;

    @NotNull
    private String name;

    @CreatedDate
    private LocalDateTime createdAt;


    //상품생성
    @Builder
    public Product(Long id, int stock_quantity, String name){
        this.id = id;
        this.stock_quantity = stock_quantity;
        this.name = name;
    }

    ///재고 감소
    // 하나의 수량만 주문 가능하다고 가정한다.
    public void reduceQuantity(){
        this.stock_quantity -= 1;
    }


}
