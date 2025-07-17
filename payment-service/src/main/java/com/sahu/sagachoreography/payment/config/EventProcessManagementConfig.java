package com.sahu.sagachoreography.payment.config;

import com.sahu.sagachoreography.common.constant.OrderStatus;
import com.sahu.sagachoreography.common.event.OrderEvent;
import com.sahu.sagachoreography.common.event.PaymentEvent;
import com.sahu.sagachoreography.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class EventProcessManagementConfig {

    private final PaymentService paymentService;

    @Bean
    public Function<Flux<OrderEvent>, Flux<PaymentEvent>> orderEventConsumerPaymentEventProducer() {
        return orderEventFlux -> orderEventFlux.flatMap(this::processOrderEvent);
    }

    private Mono<PaymentEvent> processOrderEvent(OrderEvent orderEvent) {
        log.info("Processing order event: {}", orderEvent.getUuid());
        return OrderStatus.ORDER_CREATED.equals(orderEvent.getOrderResponseDTO().orderStatus()) ?
                Mono.fromSupplier(() -> paymentService.processPaymentAndPlaceOrder(orderEvent)) :
                Mono.fromRunnable(() -> paymentService.cancelOrderAndRefundPayment(orderEvent));
    }

}
