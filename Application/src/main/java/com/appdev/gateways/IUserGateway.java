package com.appdev.gateways;

import com.appdev.entities.UserDomain;
import com.appdev.valueobject.Email;

import java.util.Optional;

public interface IUserGateway {
    Optional<UserDomain> findByEmail(Email email);
}
