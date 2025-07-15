package com.sahu.sagachoreography.common.event;

import com.sahu.sagachoreography.common.dto.OrderResponseDTO;
import com.sahu.sagachoreography.common.dto.PaymentResponseDTO;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class PaymentEvent {
    private UUID uuid = UUID.randomUUID();
    private Date date = new Date();
    @NonNull
    private PaymentResponseDTO paymentResponseDTO;
}
