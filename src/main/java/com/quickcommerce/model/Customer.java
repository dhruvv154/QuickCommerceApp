package com.quickcommerce.model;

import com.quickcommerce.exception.InvalidOperationException;
import com.quickcommerce.factory.CartFactory;
import com.quickcommerce.factory.OrderFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a Customer — the primary buyer in the Quick Commerce system.
 *
 * <p>Inherits authentication behaviour from {@link User} and adds
 * shopping-specific responsibilities: managing a {@link Cart} and
 * placing / tracking {@link Order}s.</p>
 */
public class Customer extends User {

    private String deliveryAddress;
    private Cart   cart;                             // Composition: 1-to-1
    private final List<Order> orderHistory;          // Association: 1-to-many

    public Customer(String name, String email, String password, String deliveryAddress) {
        super(name, email, password);
        this.deliveryAddress = deliveryAddress;
        this.cart            = CartFactory.createCartFor(this);
        this.orderHistory    = new ArrayList<>();
    }

    public void addToCart(Product product, int quantity) {
        if (!isLoggedIn()) {
            throw new InvalidOperationException("Customer must be logged in to add items to cart.");
        }
        cart.addItem(product, quantity);
        System.out.println("[CART] Added " + quantity + "x '" + product.getName() + "' to cart.");
    }

    public Order placeOrder() {
        if (!isLoggedIn()) {
            throw new InvalidOperationException("Customer must be logged in to place an order.");
        }
        if (cart.getCartItems().isEmpty()) {
            throw new InvalidOperationException("Cannot place an order with an empty cart.");
        }

        Order order = OrderFactory.createOrderFromCart(this, cart);
        orderHistory.add(order);

        // Clear cart after placing order
        cart = CartFactory.createCartFor(this);
        System.out.println("[ORDER] Order placed successfully. Order ID: " + order.getOrderId());
        return order;
    }

    public void trackOrder(String orderId) {
        orderHistory.stream()
                .filter(o -> o.getOrderId().equals(orderId))
                .findFirst()
                .ifPresentOrElse(
                        o -> System.out.println("[TRACK] " + o.getTrackingInfo()),
                        () -> System.out.println("[TRACK] Order not found: " + orderId)
                );
    }

    public String getDeliveryAddress()                      { return deliveryAddress; }
    public void   setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }

    public Cart   getCart()                                 { return cart; }

    public List<Order> getOrderHistory() {
        return Collections.unmodifiableList(orderHistory);
    }

    @Override
    public String getRole() { return "Customer"; }

    @Override
    public String toString() {
        return String.format("Customer{id='%s', name='%s', email='%s', address='%s'}",
                getUserId(), getName(), getEmail(), deliveryAddress);
    }
}
