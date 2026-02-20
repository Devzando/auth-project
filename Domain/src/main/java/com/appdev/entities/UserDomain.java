package com.appdev.entities;

import com.appdev.valueobject.Email;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserDomain extends BaseDomain {
    private String name;
    private Email email;
    private String password;
    private ProfileDomain profileDomain;
}
