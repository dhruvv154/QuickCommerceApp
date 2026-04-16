package com.quickcommerce.factory;

import com.quickcommerce.model.CartItem;
import com.quickcommerce.model.Product;

/**
 * Lightweight factory for creating CartItem instances.
 */
public final class CartItemFactory {
    private CartItemFactory() {}

    public static CartItem of(Product product, int qty) {
        return new CartItem(product, qty);
    }
}
