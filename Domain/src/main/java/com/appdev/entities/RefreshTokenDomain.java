package com.appdev.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RefreshTokenDomain extends BaseDomain{
    private String token;
    private UserDomain userDomain;
    @Builder.Default
    private LocalDateTime expiryDate = LocalDateTime.now().plusDays(7);
    @Builder.Default
    private boolean revoked = false;

    public boolean isExpired() {
        return expiryDate.isBefore(LocalDateTime.now());
    }

    public boolean isInvalid() {
        return revoked || isExpired();
    }

    public void revoke() {
        this.revoked = true;
    }
}
