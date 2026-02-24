package com.appdev.security;

import com.appdev.gateways.ICurrentUserProvider;
import com.appdev.usecases.auth.dtos.AuthenticatedUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Optional;
import java.util.UUID;

public class SecurityContextUserProvider implements ICurrentUserProvider {
    @Override
    public Optional<AuthenticatedUser> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.getPrincipal() instanceof Jwt jwt){
            UUID userId = UUID.fromString(jwt.getSubject());
            String role = jwt.getClaimAsString("scope");

            return Optional.of(new AuthenticatedUser(userId, role));
        }

        return Optional.empty();
    }
}
