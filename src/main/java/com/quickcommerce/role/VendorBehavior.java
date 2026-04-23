package com.quickcommerce.role;

import com.quickcommerce.model.User;

public class VendorBehavior implements RoleBehavior {

    @Override
    public void onLogin(User user) {
        System.out.println("[ROLE] Vendor-specific login actions for " + user.getName());
    }

    @Override
    public void onLogout(User user) {
        System.out.println("[ROLE] Vendor-specific logout actions for " + user.getName());
    }
}
