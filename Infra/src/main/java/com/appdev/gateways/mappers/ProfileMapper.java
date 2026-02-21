package com.appdev.gateways.mappers;

import com.appdev.entities.ProfileDomain;
import com.appdev.entities.ProfileEntity;
import com.appdev.valueobject.Role;

public class ProfileMapper {
    public ProfileEntity toEntity(ProfileDomain profileDomain){
        return ProfileEntity.builder()
                .id(profileDomain.getId())
                .role(profileDomain.getRole().value())
                .createdAt(profileDomain.getCreatedAt())
                .updatedAt(profileDomain.getUpdatedAt())
                .build();
    }

    public ProfileDomain toDomain(ProfileEntity profileEntity){
        return ProfileDomain.builder()
                .id(profileEntity.getId())
                .role(new Role(profileEntity.getRole()))
                .createdAt(profileEntity.getCreatedAt())
                .updatedAt(profileEntity.getUpdatedAt())
                .build();
    }
}
