package com.appdev.usecases.auth.dtos;

import java.util.UUID;

public record AuthenticatedUser(UUID id, String role) {
}
