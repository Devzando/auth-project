package com.appdev.usecases.auth.dtos;

public record LoginResponse(String accessToken, String refreshToken) {
}
