package com.appdev.usecases.auth;

import com.appdev.gateways.IRefreshTokenGateway;

public class LogoutUseCase {
    private final IRefreshTokenGateway refreshTokenGateway;

    public LogoutUseCase(IRefreshTokenGateway refreshTokenGateway) {
        this.refreshTokenGateway = refreshTokenGateway;
    }

    public void execute(String refreshToken){
        refreshTokenGateway.revokeByToken(refreshToken);
    }
}
