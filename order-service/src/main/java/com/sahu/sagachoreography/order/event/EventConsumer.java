package com.sahu.sagachoreography.order.event;

import com.sahu.sagachoreography.common.constant.OrderStatus;
import com.sahu.sagachoreography.common.constant.PaymentStatus;
import com.sahu.sagachoreography.common.dto.PaymentResponseDTO;
import com.sahu.sagachoreography.common.event.PaymentEvent;
import com.sahu.sagachoreography.order.entity.Order;
import com.sahu.sagachoreography.order.repository.OrderRepository;
import com.sahu.sagachoreography.order.service.util.OrderUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventConsumer {

    private final OrderRepository orderRepository;
    private final EventProducer eventProducer;

    public void consumePaymentEvent(PaymentEvent paymentEvent) {
        orderRepository.findById(paymentEvent.getPaymentResponseDTO().id())
                .ifPresent(order -> {
                    updateOrderStatus(order, paymentEvent.getPaymentResponseDTO());
                });
    }

    private void updateOrderStatus(Order order, PaymentResponseDTO paymentResponseDTO) {
        log.info("Updating order {} status based on payment response", order.getId());
        boolean isPaymentSuccess = paymentResponseDTO.paymentStatus().equals(PaymentStatus.PAYMENT_COMPLETED);
        log.info("Payment status for order {}: {}", order.getId(), isPaymentSuccess ? "SUCCESS" : "FAILED");

        order.setPaymentStatus(paymentResponseDTO.paymentStatus());
        order.setOrderStatus(isPaymentSuccess? OrderStatus.ORDER_CONFIRMED : OrderStatus.ORDER_CANCELLED);
        orderRepository.save(order);

        if(!isPaymentSuccess) {
            eventProducer.produceOrderEvent(OrderUtil.orderResponseDTO(order));
        }
    }

}
