package com.appdev.usecases.auth;

import com.appdev.entities.UserDomain;
import com.appdev.exceptions.ConflictException;
import com.appdev.exceptions.InvalidCredentialException;
import com.appdev.gateways.IJwtGateway;
import com.appdev.gateways.IPasswordHasherGateway;
import com.appdev.gateways.IUserGateway;
import com.appdev.usecases.auth.dtos.LoginRequest;
import com.appdev.usecases.auth.dtos.LoginResponse;
import com.appdev.valueobject.Email;

public class LoginUseCase {
    private final IUserGateway userGateway;
    private final IJwtGateway jwtGateway;
    private final IPasswordHasherGateway passwordHasherGateway;

    public LoginUseCase(IUserGateway userGateway, IJwtGateway jwtGateway, IPasswordHasherGateway passwordHasherGateway) {
        this.userGateway = userGateway;
        this.jwtGateway = jwtGateway;
        this.passwordHasherGateway = passwordHasherGateway;
    }

    public LoginResponse execute(LoginRequest request){
        UserDomain userDomain = userGateway.findByEmail(new Email(request.email()))
                .orElseThrow(() -> new ConflictException("E-mail ou senha incorretos"));

        if(!passwordHasherGateway.matches(request.password(), userDomain.getPassword()))
            throw new InvalidCredentialException("E-mail ou senha incorretos");

        String accessToken = jwtGateway.generateToken(userDomain);

        return new LoginResponse(accessToken);
    }
}
