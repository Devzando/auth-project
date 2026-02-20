package com.appdev.gateways;

import com.appdev.entities.UserDomain;
import com.appdev.gateways.mappers.UserMapper;
import com.appdev.persistence.UserRepository;
import com.appdev.valueobject.Email;

import java.util.Optional;

public class UserRepositoryGateway implements IUserGateway {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserRepositoryGateway(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public Optional<UserDomain> findByEmail(Email email) {
        return userRepository.findByEmail(email.address())
                .map(userMapper::toDomain);
    }
}
