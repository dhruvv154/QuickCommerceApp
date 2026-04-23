package com.quickcommerce.payment.handler;

import com.quickcommerce.model.Payment;

public class CaptureHandler extends PaymentHandler {

    @Override
    protected boolean process(Payment payment) {
        boolean processed = payment.processPayment();
        System.out.println("[PAYMENT-CHAIN] CaptureHandler -> processed=" + processed);
        return processed;
    }
}
