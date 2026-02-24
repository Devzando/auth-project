package com.appdev.gateways;

import com.appdev.entities.UserDomain;
import com.appdev.entities.UserEntity;
import com.appdev.gateways.mappers.UserMapper;
import com.appdev.persistence.UserRepository;
import com.appdev.valueobject.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class UserRepositoryGateway implements IUserGateway {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDomain> findByEmail(Email email) {
        return userRepository.findByEmail(email.address())
                .map(userMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDomain> findById(UUID id) {
        return userRepository.findById(id)
                .map(userMapper::toDomain);
    }

    @Override
    @Transactional
    public UserDomain save(UserDomain userDomain) {
        UserEntity userEntity = userMapper.toEntity(userDomain);
        return userMapper.toDomain(userRepository.save(userEntity));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(Email email) {
        return userRepository.existsByEmail(email.address());
    }
}
