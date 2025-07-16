package com.sahu.sagachoreography.common.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderRequestDTO(
    Long productId,
    Long userId,
    Integer quantity,
    BigDecimal price
){

}
