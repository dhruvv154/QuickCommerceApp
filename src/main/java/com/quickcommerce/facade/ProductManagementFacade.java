package com.quickcommerce.facade;

import com.quickcommerce.model.Product;
import com.quickcommerce.model.Vendor;
import com.quickcommerce.service.ProductService;
import com.quickcommerce.service.UserService;

/**
 * Minimal facade that offers a simplified API for product/vendor/admin
 * workflows without changing existing services. Controllers can use this
 * when a single entry-point is preferable.
 */
public class ProductManagementFacade {

    private final ProductService productService;
    private final UserService userService;

    public ProductManagementFacade(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    public void addProduct(Product product, Vendor vendor) {
        // register product with vendor catalogue and the global product service
        vendor.addProduct(product);
        productService.addProduct(product);
    }

    public void approveVendor(Vendor vendor) {
        // lightweight approval: attempt to register the vendor if not already present
        try {
            userService.registerUser(vendor);
        } catch (Exception ex) {
            // already registered or persistence issue — keep behaviour idempotent
        }
        System.out.println("[FACADE] Vendor '" + vendor.getName() + "' approved.");
    }

    public void listVendorProduct(Product product) {
        productService.addProduct(product);
    }
}
