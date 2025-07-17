package com.sahu.sagachoreography.payment.service;

import com.sahu.sagachoreography.common.event.OrderEvent;
import com.sahu.sagachoreography.common.event.PaymentEvent;

public interface PaymentService {

    PaymentEvent processPaymentAndPlaceOrder(OrderEvent orderEvent);

    void cancelOrderAndRefundPayment(OrderEvent orderEvent);

}
