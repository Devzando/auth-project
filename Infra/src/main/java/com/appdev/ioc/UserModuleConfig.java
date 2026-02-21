package com.appdev.ioc;

import com.appdev.gateways.*;
import com.appdev.gateways.mappers.ProfileMapper;
import com.appdev.gateways.mappers.UserMapper;
import com.appdev.persistence.ProfileRepository;
import com.appdev.persistence.UserRepository;
import com.appdev.usecases.user.CreateUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class UserModuleConfig {
    // Gateways
    @Bean
    IUserGateway iUserGateway(UserRepository userRepository, UserMapper userMapper){
        return new UserRepositoryGateway(userRepository, userMapper);
    }

    // Mappers
    @Bean
    UserMapper userMapper(ProfileMapper profileMapper){
        return new UserMapper(profileMapper);
    }

    // Use Cases
    @Bean
    CreateUserUseCase createUserUseCase(IUserGateway userGateway, IProfileGateway profileGateway, IPasswordHasherGateway passwordHasherGateway){
        return new CreateUserUseCase(userGateway, passwordHasherGateway, profileGateway);
    }
}
