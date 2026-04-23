package com.quickcommerce.payment.handler;

import com.quickcommerce.model.Payment;

/**
 * Simple builder for a default payment processing chain.
 */
public final class PaymentProcessingChain {
    private PaymentProcessingChain() {}

    public static PaymentHandler defaultChain() {
        PaymentHandler validation = new ValidationHandler();
        PaymentHandler auth = new AuthorizationHandler();
        PaymentHandler capture = new CaptureHandler();
        PaymentHandler inventory = new InventoryUpdateHandler();

        validation.setNext(auth).setNext(capture).setNext(inventory);
        return validation;
    }

    public static void process(Payment payment) {
        PaymentHandler chain = defaultChain();
        chain.handle(payment);
    }
}
