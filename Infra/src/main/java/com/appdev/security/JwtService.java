package com.appdev.security;

import com.appdev.entities.UserDomain;
import com.appdev.gateways.IJwtGateway;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import java.time.Instant;

public class JwtService implements IJwtGateway {
    private final JwtEncoder encoder;

    public JwtService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public String generateToken(UserDomain userDomain){
        Instant now = Instant.now();
        long expiry = 3600L;

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("spring-security-jwt")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(userDomain.getId().toString())
                .claim("scope", userDomain.getProfileDomain().getRole().value())
                .build();

        return encoder.encode(
                        JwtEncoderParameters.from(claims))
                .getTokenValue();
    }
}
