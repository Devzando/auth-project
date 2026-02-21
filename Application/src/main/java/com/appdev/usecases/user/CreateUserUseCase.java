package com.appdev.usecases.user;

import com.appdev.entities.ProfileDomain;
import com.appdev.entities.UserDomain;
import com.appdev.exceptions.ConflictException;
import com.appdev.exceptions.NotFoundException;
import com.appdev.gateways.IPasswordHasherGateway;
import com.appdev.gateways.IProfileGateway;
import com.appdev.gateways.IUserGateway;
import com.appdev.usecases.user.dtos.CreateUserDTO;
import com.appdev.valueobject.Email;
import com.appdev.valueobject.Role;

public class CreateUserUseCase {
    private final IUserGateway userGateway;
    private final IPasswordHasherGateway passwordHasherGateway;
    private final IProfileGateway profileGateway;

    public CreateUserUseCase(IUserGateway userGateway, IPasswordHasherGateway passwordHasherGateway, IProfileGateway profileGateway) {
        this.userGateway = userGateway;
        this.passwordHasherGateway = passwordHasherGateway;
        this.profileGateway = profileGateway;
    }

    public void execute(CreateUserDTO request){
        boolean userAlreadyExist = userGateway.existsByEmail(new Email(request.email()));

        if(userAlreadyExist)
            throw new ConflictException("Erro ao criar usuário");

        ProfileDomain profile = profileGateway.findByRole(new Role(request.role()))
                .orElseThrow(() -> new NotFoundException("Role não existe"));

        UserDomain newUser = UserDomain.builder()
                .email(new Email(request.email()))
                .name(request.name())
                .password(passwordHasherGateway.hash(request.password()))
                .profileDomain(profile)
                .build();

        userGateway.save(newUser);
    }
}
