package com.quickcommerce.payment.handler;

import com.quickcommerce.model.Payment;

public class ValidationHandler extends PaymentHandler {

    @Override
    protected boolean process(Payment payment) {
        boolean ok = payment.validatePayment();
        System.out.println("[PAYMENT-CHAIN] ValidationHandler -> " + ok);
        return ok;
    }
}
