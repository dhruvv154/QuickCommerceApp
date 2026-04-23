package com.quickcommerce.payment.handler;

import com.quickcommerce.model.Payment;

/**
 * Base handler for a Chain of Responsibility for payment processing.
 */
public abstract class PaymentHandler {
    protected PaymentHandler next;

    public PaymentHandler setNext(PaymentHandler next) {
        this.next = next;
        return next;
    }

    public void handle(Payment payment) {
        if (process(payment) && next != null) {
            next.handle(payment);
        }
    }

    /**
     * Process current step. Return true to continue the chain.
     */
    protected abstract boolean process(Payment payment);
}
