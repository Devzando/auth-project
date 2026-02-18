package com.appdev.entities;

import com.appdev.valueobject.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileDomain extends BaseDomain {
    private Role role;
}
