package com.appdev.gateways;

import com.appdev.entities.UserDomain;
import com.appdev.valueobject.Email;

import java.util.Optional;
import java.util.UUID;

public interface IUserGateway {
    Optional<UserDomain> findByEmail(Email email);
    Optional<UserDomain> findById(UUID id);
    UserDomain save(UserDomain userDomain);
    boolean existsByEmail(Email email);
}
