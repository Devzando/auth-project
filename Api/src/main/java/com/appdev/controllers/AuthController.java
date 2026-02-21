package com.appdev.controllers;

import com.appdev.controllers.requests.auth.AuthRequest;
import com.appdev.usecases.auth.LoginUseCase;
import com.appdev.usecases.auth.dtos.LoginRequest;
import com.appdev.usecases.auth.dtos.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Endpoints relacionados com processos de autenticação")
public class AuthController {
    private final LoginUseCase loginUseCase;

    public AuthController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @Operation(summary = "Realizar login", description = "Endpoint para obter um token de acesso ao sistema")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody AuthRequest request){
        LoginRequest loginRequest = new LoginRequest(request.getEmail(), request.getPassword());

        LoginResponse loginResponse = loginUseCase.execute(loginRequest);

        return ResponseEntity.ok(loginResponse);
    }
}
