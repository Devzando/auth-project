package com.appdev.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleType {
    ADMIN("ADMIN"),
    BASIC("BASIC");

    private final String value;
}
