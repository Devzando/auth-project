package com.appdev.usecases.user.dtos;

public record CreateUserRequest(String name, String email, String password, String role) {
}
