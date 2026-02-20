package com.appdev.entities;

import com.appdev.valueobject.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ProfileDomain extends BaseDomain {
    private Role role;
}
