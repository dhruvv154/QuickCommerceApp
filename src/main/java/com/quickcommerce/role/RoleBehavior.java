package com.quickcommerce.role;

import com.quickcommerce.model.User;

/**
 * Role-specific behaviour interface (Strategy). Keep implementations small
 * so callers can delegate role-dependent logic without branching on type.
 */
public interface RoleBehavior {
    void onLogin(User user);
    void onLogout(User user);
}
