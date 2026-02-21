package com.appdev.gateways.mappers;

import com.appdev.entities.RefreshTokenDomain;
import com.appdev.entities.RefreshTokenEntity;

public class RefreshTokenMapper {
    private final UserMapper userMapper;

    public RefreshTokenMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public RefreshTokenEntity toEntity(RefreshTokenDomain refreshTokenDomain){
        return RefreshTokenEntity.builder()
                .id(refreshTokenDomain.getId())
                .token(refreshTokenDomain.getToken())
                .expiryDate(refreshTokenDomain.getExpiryDate())
                .revoked(refreshTokenDomain.isRevoked())
                .createdAt(refreshTokenDomain.getCreatedAt())
                .updatedAt(refreshTokenDomain.getUpdatedAt())
                .user(userMapper.toEntity(refreshTokenDomain.getUserDomain()))
                .build();
    }

    public RefreshTokenDomain toDomain(RefreshTokenEntity refreshTokenEntity){
        return RefreshTokenDomain.builder()
                .id(refreshTokenEntity.getId())
                .token(refreshTokenEntity.getToken())
                .expiryDate(refreshTokenEntity.getExpiryDate())
                .revoked(refreshTokenEntity.isRevoked())
                .createdAt(refreshTokenEntity.getCreatedAt())
                .updatedAt(refreshTokenEntity.getUpdatedAt())
                .userDomain(userMapper.toDomain(refreshTokenEntity.getUser()))
                .build();
    }
}
