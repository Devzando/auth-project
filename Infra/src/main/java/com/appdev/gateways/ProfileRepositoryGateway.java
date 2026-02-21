package com.appdev.gateways;

import com.appdev.entities.ProfileDomain;
import com.appdev.gateways.mappers.ProfileMapper;
import com.appdev.persistence.ProfileRepository;
import com.appdev.valueobject.Role;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class ProfileRepositoryGateway implements IProfileGateway{
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    public ProfileRepositoryGateway(ProfileRepository profileRepository, ProfileMapper profileMapper) {
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProfileDomain> findByRole(Role role) {
        return profileRepository.findByRole(role.value())
                .map(profileMapper::toDomain);
    }
}
