package com.sahu.sagachoreography.order.config;

import com.sahu.sagachoreography.common.event.PaymentEvent;
import com.sahu.sagachoreography.order.event.EventConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class EventConsumerConfig {

    private final EventConsumer eventConsumer;

    @Bean
    public Consumer<PaymentEvent> paymentEventConsumer() {
        return eventConsumer::consumePaymentEvent;
    }

}
