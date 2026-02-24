package com.appdev.jobs;

import com.appdev.persistence.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class TokenCleanupTask {
    private final RefreshTokenRepository refreshTokenRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void cleanupTokens(){
        refreshTokenRepository.deleteRevokedOrExpiredBefore(LocalDateTime.now());
    }
}
