package com.appdev.gateways;

public interface IPasswordHasherGateway {
    String hash(String password);
    boolean matches(String rawPassword, String passwordHash);
}
