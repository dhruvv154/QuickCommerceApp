package com.quickcommerce.factory;

import com.quickcommerce.model.Cart;
import com.quickcommerce.model.Customer;

/**
 * Lightweight factory for creating Cart instances.
 * Keeps construction logic centralized and easy to mock/extend.
 */
public final class CartFactory {

    private CartFactory() {}

    public static Cart createCartFor(Customer owner) {
        return new Cart(owner);
    }
}
