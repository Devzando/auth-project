package com.appdev.gateways;

import com.appdev.entities.RefreshTokenDomain;
import com.appdev.entities.RefreshTokenEntity;
import com.appdev.gateways.mappers.RefreshTokenMapper;
import com.appdev.persistence.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class RefreshTokenRepositoryGateway implements IRefreshTokenGateway {
    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenMapper refreshTokenMapper;

    @Override
    @Transactional
    public RefreshTokenDomain save(RefreshTokenDomain refreshTokenDomain) {
        RefreshTokenEntity refreshTokenEntity = refreshTokenMapper.toEntity(refreshTokenDomain);
        return refreshTokenMapper.toDomain(refreshTokenRepository.save(refreshTokenEntity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RefreshTokenDomain> findByToken(String token) {
        return refreshTokenRepository.findByToken(token)
                .map(refreshTokenMapper::toDomain);
    }

    @Override
    @Transactional
    public void revokeByToken(String token) {
        refreshTokenRepository.findByToken(token).ifPresent(entity -> {
            RefreshTokenDomain domain = refreshTokenMapper.toDomain(entity);
            domain.revoke();
            refreshTokenRepository.save(refreshTokenMapper.toEntity(domain));
        });
    }

    @Override
    @Transactional
    public void revokeAllByUserId(UUID userId) {
        refreshTokenRepository.revokeAllByUserId(userId);
    }
}
