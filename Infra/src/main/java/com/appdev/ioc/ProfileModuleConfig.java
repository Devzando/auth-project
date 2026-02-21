package com.appdev.ioc;

import com.appdev.gateways.IProfileGateway;
import com.appdev.gateways.ProfileRepositoryGateway;
import com.appdev.gateways.mappers.ProfileMapper;
import com.appdev.persistence.ProfileRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProfileModuleConfig {
    // Gateways
    @Bean
    IProfileGateway iProfileGateway(ProfileRepository profileRepository, ProfileMapper profileMapper){
        return new ProfileRepositoryGateway(profileRepository, profileMapper);
    }

    // Mappers
    @Bean
    ProfileMapper profileMapper(){
        return new ProfileMapper();
    }
}
