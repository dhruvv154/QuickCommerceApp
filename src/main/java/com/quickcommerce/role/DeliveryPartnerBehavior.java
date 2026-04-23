package com.quickcommerce.role;

import com.quickcommerce.model.User;

public class DeliveryPartnerBehavior implements RoleBehavior {

    @Override
    public void onLogin(User user) {
        System.out.println("[ROLE] DeliveryPartner-specific login actions for " + user.getName());
    }

    @Override
    public void onLogout(User user) {
        System.out.println("[ROLE] DeliveryPartner-specific logout actions for " + user.getName());
    }
}
