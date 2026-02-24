package com.appdev.gateways;

import com.appdev.entities.ProfileDomain;
import com.appdev.gateways.mappers.ProfileMapper;
import com.appdev.persistence.ProfileRepository;
import com.appdev.valueobject.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
public class ProfileRepositoryGateway implements IProfileGateway{
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<ProfileDomain> findByRole(Role role) {
        return profileRepository.findByRole(role.value())
                .map(profileMapper::toDomain);
    }
}
