package com.quickcommerce.model;

public class ProductBuilder {

    private String name;
    private String category;
    private double price;
    private int stockQuantity;

    // Optional: for reconstruction use-case
    private String productId;
    private boolean useExistingId = false;

    // -------------------------
    // Builder methods
    // -------------------------

    public ProductBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder category(String category) {
        this.category = category;
        return this;
    }

    public ProductBuilder price(double price) {
        this.price = price;
        return this;
    }

    public ProductBuilder stock(int stockQuantity) {
        this.stockQuantity = stockQuantity;
        return this;
    }

    // Optional (for DB reconstruction)
    public ProductBuilder productId(String productId) {
        this.productId = productId;
        this.useExistingId = true;
        return this;
    }

    // -------------------------
    // Build method
    // -------------------------

    public Product build() {
        if (name == null || category == null) {
            throw new IllegalStateException("Name and Category are required");
        }

        if (useExistingId) {
            return new Product(productId, name, category, price, stockQuantity);
        }

        return new Product(name, category, price, stockQuantity);
    }
}