package com.sahu.sagachoreography.common.dto;

import com.sahu.sagachoreography.common.constant.OrderStatus;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderResponseDTO(
        Long id,
        Long productId,
        Long userId,
        Integer quantity,
        BigDecimal price,
        OrderStatus orderStatus
){

}
