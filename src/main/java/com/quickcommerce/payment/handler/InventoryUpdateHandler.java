package com.quickcommerce.payment.handler;

import com.quickcommerce.model.Payment;

public class InventoryUpdateHandler extends PaymentHandler {

    @Override
    protected boolean process(Payment payment) {
        // Inventory updates are handled elsewhere; simulate success here
        System.out.println("[PAYMENT-CHAIN] InventoryUpdateHandler -> inventory updated");
        return true;
    }
}
