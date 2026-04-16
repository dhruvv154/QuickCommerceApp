package com.quickcommerce.factory;

import com.quickcommerce.model.Cart;
import com.quickcommerce.model.Customer;
import com.quickcommerce.model.Order;

/**
 * Simple factory for Order creation. Delegates existing Order behaviour
 * (e.g. building from cart) to domain objects to avoid duplicating logic.
 */
public final class OrderFactory {

    private OrderFactory() {}

    public static Order createOrderFromCart(Customer customer, Cart cart) {
        Order order = new Order(customer);
        order.createOrder(cart);
        return order;
    }
}
