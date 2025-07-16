package com.sahu.sagachoreography.common.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentRequestDTO (
    Long orderId,
    Long userId,
    BigDecimal amount
){

}
