package com.appdev.gateways.mappers;

import com.appdev.entities.UserDomain;
import com.appdev.entities.UserEntity;
import com.appdev.valueobject.Email;

public class UserMapper {
    private final ProfileMapper profileMapper;

    public UserMapper(ProfileMapper profileMapper) {
        this.profileMapper = profileMapper;
    }

    public UserEntity toEntity(UserDomain userDomain){
        return UserEntity.builder()
                .id(userDomain.getId())
                .email(userDomain.getEmail().address())
                .password(userDomain.getPassword())
                .name(userDomain.getName())
                .createdAt(userDomain.getCreatedAt())
                .updatedAt(userDomain.getUpdatedAt())
                .profile(profileMapper.toEntity(userDomain.getProfileDomain()))
                .build();
    }

    public UserDomain toDomain(UserEntity userEntity){
        return UserDomain.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .password(userEntity.getPassword())
                .email(new Email(userEntity.getEmail()))
                .createdAt(userEntity.getCreatedAt())
                .updatedAt(userEntity.getUpdatedAt())
                .profileDomain(profileMapper.toDomain(userEntity.getProfile()))
                .build();
    }
}
