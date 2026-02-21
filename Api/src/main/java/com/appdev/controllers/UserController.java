package com.appdev.controllers;

import com.appdev.controllers.requests.user.AccountRegisterRequest;
import com.appdev.controllers.requests.user.CreateUserRequest;
import com.appdev.controllers.responses.MessageResponse;
import com.appdev.enums.RoleType;
import com.appdev.usecases.user.CreateUserUseCase;
import com.appdev.usecases.user.dtos.CreateUserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@Tag(name = "Usuarios", description = "Endpoints para gerenciamento de usuários")
public class UserController {
    private final CreateUserUseCase createUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @Operation(summary = "Criar um novo usuário",
            description = "Endpoint para registro de novos usuários no sistema. A role pode ser ADMIN ou BASIC")
    @PostMapping
    public ResponseEntity<MessageResponse> createUser(@Valid @RequestBody CreateUserRequest request){
        CreateUserDTO userDTO = new CreateUserDTO(request.getName(), request.getEmail(), request.getPassword(), request.getRole());

        createUserUseCase.execute(userDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new MessageResponse("Usuário criado com sucesso!"));
    }

    @Operation(summary = "Adicionar um novo registro de conta",
            description = "Endpoint para registro de novos contas de usuários no sistema. A role pode ser ADMIN ou BASIC")
    @PostMapping("register")
    public ResponseEntity<MessageResponse> createAccountUser(@Valid @RequestBody AccountRegisterRequest request){
        CreateUserDTO userDTO = new CreateUserDTO(request.getName(), request.getEmail(), request.getPassword(), RoleType.BASIC.getValue());

        createUserUseCase.execute(userDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new MessageResponse("Conta adicionada com sucesso!"));
    }
}
