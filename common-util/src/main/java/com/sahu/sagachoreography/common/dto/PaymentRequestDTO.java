package com.sahu.sagachoreography.common.dto;

import lombok.Builder;

@Builder
public record PaymentRequestDTO (
    Long orderId,
    Long userId,
    Double amount
){

}
