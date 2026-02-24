package com.appdev.usecases.auth;

import com.appdev.gateways.ICurrentUserProvider;
import com.appdev.gateways.IRefreshTokenGateway;

import java.util.UUID;

public class LogoutAllAccountUseCase {
    private final IRefreshTokenGateway refreshTokenGateway;
    private final ICurrentUserProvider currentUserProvider;

    public LogoutAllAccountUseCase(IRefreshTokenGateway refreshTokenGateway, ICurrentUserProvider currentUserProvider) {
        this.refreshTokenGateway = refreshTokenGateway;
        this.currentUserProvider = currentUserProvider;
    }

    public void execute(){
        UUID userId = currentUserProvider.getUser().get().id();
        refreshTokenGateway.revokeAllByUserId(userId);
    }
}
