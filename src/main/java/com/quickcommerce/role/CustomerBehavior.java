package com.quickcommerce.role;

import com.quickcommerce.model.User;

public class CustomerBehavior implements RoleBehavior {

    @Override
    public void onLogin(User user) {
        System.out.println("[ROLE] Customer-specific login actions for " + user.getName());
    }

    @Override
    public void onLogout(User user) {
        System.out.println("[ROLE] Customer-specific logout actions for " + user.getName());
    }
}
