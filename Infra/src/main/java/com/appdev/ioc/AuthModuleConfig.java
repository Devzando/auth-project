package com.appdev.ioc;

import com.appdev.gateways.*;
import com.appdev.gateways.mappers.RefreshTokenMapper;
import com.appdev.gateways.mappers.UserMapper;
import com.appdev.persistence.RefreshTokenRepository;
import com.appdev.security.JwtService;
import com.appdev.usecases.auth.LoginUseCase;
import com.appdev.usecases.auth.RefreshTokenUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class AuthModuleConfig {
    // Gateways
    @Bean
    IPasswordHasherGateway passwordHasherGateway(PasswordEncoder passwordEncoder){
        return new PasswordHasherGateway(passwordEncoder);
    }

    @Bean
    IJwtGateway iJwtGateway(JwtEncoder jwtEncoder){
        return new JwtService(jwtEncoder);
    }

    @Bean
    IRefreshTokenGateway iRefreshTokenGateway(RefreshTokenRepository refreshTokenRepository, RefreshTokenMapper refreshTokenMapper){
        return new RefreshTokenRepositoryGateway(refreshTokenRepository, refreshTokenMapper);
    }

    // Mappers
    @Bean
    RefreshTokenMapper refreshTokenMapper(UserMapper userMapper){
        return new RefreshTokenMapper(userMapper);
    }

    // Use cases
    @Bean
    LoginUseCase loginUseCase(
            IUserGateway userGateway,
            IJwtGateway jwtGateway,
            IPasswordHasherGateway passwordHasherGateway,
            IRefreshTokenGateway refreshTokenGateway){
        return new LoginUseCase(userGateway, jwtGateway, passwordHasherGateway, refreshTokenGateway);
    }

    @Bean
    RefreshTokenUseCase refreshTokenUseCase(IRefreshTokenGateway refreshTokenGateway, IJwtGateway jwtGateway, IUserGateway userGateway){
        return new RefreshTokenUseCase(refreshTokenGateway, jwtGateway, userGateway);
    }
}
