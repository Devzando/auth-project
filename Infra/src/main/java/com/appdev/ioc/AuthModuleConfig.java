package com.appdev.ioc;

import com.appdev.gateways.IJwtGateway;
import com.appdev.gateways.IPasswordHasherGateway;
import com.appdev.gateways.IUserGateway;
import com.appdev.gateways.PasswordHasherGateway;
import com.appdev.security.JwtService;
import com.appdev.usecases.auth.LoginUseCase;
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

    // Use cases
    @Transactional(readOnly = true)
    @Bean
    LoginUseCase loginUseCase(IUserGateway userGateway, IJwtGateway jwtGateway, IPasswordHasherGateway passwordHasherGateway){
        return new LoginUseCase(userGateway, jwtGateway, passwordHasherGateway);
    }
}
