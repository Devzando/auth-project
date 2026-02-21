package com.appdev.gateways;

import com.appdev.entities.UserDomain;

import java.util.UUID;

public interface IJwtGateway {
    String generateToken(UserDomain userDomain);
    String generateRefreshToken();
}
