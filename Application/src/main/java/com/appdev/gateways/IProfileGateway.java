package com.appdev.gateways;

import com.appdev.entities.ProfileDomain;
import com.appdev.valueobject.Role;

import java.util.List;
import java.util.Optional;

public interface IProfileGateway {
    Optional<ProfileDomain> findByRole(Role role);
}
