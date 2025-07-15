package com.sahu.sagachoreography.common.dto;

import com.sahu.sagachoreography.common.constant.PaymentStatus;
import lombok.Builder;

@Builder
public record PaymentResponseDTO(
        Long id,
        Long orderId,
        Long userId,
        Double amount,
        PaymentStatus paymentStatus
) {
}
