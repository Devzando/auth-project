package com.appdev.gateways;

import com.appdev.usecases.auth.dtos.AuthenticatedUser;

import java.util.Optional;

public interface ICurrentUserProvider {
    Optional<AuthenticatedUser> getUser();
}
