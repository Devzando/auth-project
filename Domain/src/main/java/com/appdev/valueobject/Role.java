package com.appdev.valueobject;

import com.appdev.enums.RoleType;

import java.util.Arrays;

public record Role(String value) {
    public Role {
        boolean isValid = value != null && Arrays.stream(RoleType.values())
                .anyMatch(t -> t.getValue().equalsIgnoreCase(value));

        if (!isValid) {
            throw new IllegalArgumentException("Role inválida");
        }
    }

    public boolean isAdmin() {
        return RoleType.ADMIN.getValue().equalsIgnoreCase(this.value);
    }
}
