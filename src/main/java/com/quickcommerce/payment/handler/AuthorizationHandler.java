package com.quickcommerce.payment.handler;

import com.quickcommerce.model.Payment;

public class AuthorizationHandler extends PaymentHandler {

    @Override
    protected boolean process(Payment payment) {
        // For the minimal implementation we assume authorization always succeeds
        System.out.println("[PAYMENT-CHAIN] AuthorizationHandler -> authorized");
        return true;
    }
}
