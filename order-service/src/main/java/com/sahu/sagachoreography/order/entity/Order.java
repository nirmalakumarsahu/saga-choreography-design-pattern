package com.sahu.sagachoreography.order.entity;

import com.sahu.sagachoreography.common.constant.OrderStatus;
import com.sahu.sagachoreography.common.constant.PaymentStatus;
import com.sun.jdi.LongValue;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "saga_choreography_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Long userId;
    private Integer quantity;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}
