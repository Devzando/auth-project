package com.appdev.usecases.auth;

import com.appdev.entities.RefreshTokenDomain;
import com.appdev.entities.UserDomain;
import com.appdev.exceptions.ConflictException;
import com.appdev.exceptions.NotFoundException;
import com.appdev.gateways.IJwtGateway;
import com.appdev.gateways.IRefreshTokenGateway;
import com.appdev.gateways.IUserGateway;
import com.appdev.usecases.auth.dtos.LoginResponse;

import java.time.LocalDateTime;

public class RefreshTokenUseCase {
    private final IRefreshTokenGateway refreshTokenGateway;
    private final IJwtGateway jwtGateway;
    private final IUserGateway userGateway;

    public RefreshTokenUseCase(IRefreshTokenGateway refreshTokenGateway, IJwtGateway jwtGateway, IUserGateway userGateway) {
        this.refreshTokenGateway = refreshTokenGateway;
        this.jwtGateway = jwtGateway;
        this.userGateway = userGateway;
    }

    public LoginResponse execute(String oldTokenStr){
        RefreshTokenDomain oldToken = refreshTokenGateway.findByToken(oldTokenStr)
                .orElseThrow(() -> new NotFoundException("Refresh token não encontrando"));

        if(oldToken.isInvalid()){
            refreshTokenGateway.revokeAllByUserId(oldToken.getUserDomain().getId());
            throw new ConflictException("Token expirado ou revogado");
        }

        UserDomain user = userGateway.findById(oldToken.getUserDomain().getId())
                .orElseThrow(() -> new NotFoundException("Usuário inválido"));

        refreshTokenGateway.revokeByToken(oldTokenStr);

        String newAccessToken = jwtGateway.generateToken(user);
        String newRefreshTokenStr = jwtGateway.generateRefreshToken();

        refreshTokenGateway.save(RefreshTokenDomain.builder()
                .token(newRefreshTokenStr)
                .userDomain(user)
                .expiryDate(LocalDateTime.now().plusDays(7))
                .build());

        return new LoginResponse(newAccessToken, newRefreshTokenStr);
    }
}
