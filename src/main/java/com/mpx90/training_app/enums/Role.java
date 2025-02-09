package com.mpx90.training_app.enums;

import java.util.Set;

public enum Role {
    ADMIN(Set.of(Permission.READ_USER, Permission.CREATE_USER, Permission.DELETE_USER, Permission.ACCESS_ADMIN)),
    USER(Set.of(Permission.READ_USER));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
}
