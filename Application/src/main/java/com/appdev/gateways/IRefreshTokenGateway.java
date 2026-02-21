package com.appdev.gateways;

import com.appdev.entities.RefreshTokenDomain;

import java.util.Optional;
import java.util.UUID;

public interface IRefreshTokenGateway {
    RefreshTokenDomain save(RefreshTokenDomain refreshTokenDomain);
    Optional<RefreshTokenDomain> findByToken(String token);
    void revokeByToken(String token);
    void revokeAllByUserId(UUID userId);
}
