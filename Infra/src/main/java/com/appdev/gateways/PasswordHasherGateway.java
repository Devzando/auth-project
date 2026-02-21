package com.appdev.gateways;

import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordHasherGateway implements IPasswordHasherGateway {
    private final PasswordEncoder passwordEncoder;

    public PasswordHasherGateway(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String hash(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean matches(String rawPassword, String passwordHash) {
        return passwordEncoder.matches(rawPassword, passwordHash);
    }
}
