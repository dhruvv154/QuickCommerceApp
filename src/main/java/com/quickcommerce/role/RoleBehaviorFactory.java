package com.quickcommerce.role;

import com.quickcommerce.model.Customer;
import com.quickcommerce.model.DeliveryPartner;
import com.quickcommerce.model.User;
import com.quickcommerce.model.Vendor;

/**
 * Small factory to pick a RoleBehavior based on user type.
 */
public final class RoleBehaviorFactory {
    private RoleBehaviorFactory() {}

    public static RoleBehavior forUser(User u) {
        if (u instanceof Customer) return new CustomerBehavior();
        if (u instanceof Vendor) return new VendorBehavior();
        if (u instanceof DeliveryPartner) return new DeliveryPartnerBehavior();
        // fallback behaviour
        return new CustomerBehavior();
    }
}
