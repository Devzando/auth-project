package com.appdev.gateways;

import com.appdev.entities.UserDomain;

public interface IJwtGateway {
    String generateToken(UserDomain userDomain);
}
