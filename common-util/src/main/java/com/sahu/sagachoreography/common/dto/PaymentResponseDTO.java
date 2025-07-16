package com.sahu.sagachoreography.common.dto;

import com.sahu.sagachoreography.common.constant.PaymentStatus;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentResponseDTO(
        Long id,
        Long orderId,
        Long userId,
        BigDecimal amount,
        PaymentStatus paymentStatus
) {
}
