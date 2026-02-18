package com.appdev.entities;

import com.appdev.valueobject.Email;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDomain extends BaseDomain {
    private String name;
    private Email email;
    private String password;
    private ProfileDomain profileDomain;
}
