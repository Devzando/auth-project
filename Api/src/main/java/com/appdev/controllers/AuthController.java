package com.appdev.controllers;

import com.appdev.controllers.requests.auth.AuthRequest;
import com.appdev.controllers.requests.auth.LogoutRequest;
import com.appdev.controllers.requests.auth.RefreshTokenRequest;
import com.appdev.controllers.responses.MessageResponse;
import com.appdev.usecases.auth.LoginUseCase;
import com.appdev.usecases.auth.LogoutAllAccountUseCase;
import com.appdev.usecases.auth.LogoutUseCase;
import com.appdev.usecases.auth.RefreshTokenUseCase;
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
    private final RefreshTokenUseCase refreshTokenUseCase;
    private final LogoutUseCase logoutUseCase;
    private final LogoutAllAccountUseCase logoutAllAccountUseCase;

    public AuthController(
            LoginUseCase loginUseCase,
            RefreshTokenUseCase refreshTokenUseCase,
            LogoutUseCase logoutUseCase,
            LogoutAllAccountUseCase logoutAllAccountUseCase) {
        this.loginUseCase = loginUseCase;
        this.refreshTokenUseCase = refreshTokenUseCase;
        this.logoutUseCase = logoutUseCase;
        this.logoutAllAccountUseCase = logoutAllAccountUseCase;
    }

    @Operation(summary = "Realizar login", description = "Endpoint para obter um token de acesso ao sistema")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody AuthRequest request){
        LoginRequest loginRequest = new LoginRequest(request.getEmail(), request.getPassword());

        LoginResponse loginResponse = loginUseCase.execute(loginRequest);

        return ResponseEntity.ok(loginResponse);
    }

    @Operation(summary = "Gerar um novo refreshToken", description = "Endpoint para obter um novo refreshToken")
    @PostMapping("refresh")
    public ResponseEntity<LoginResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request){
        LoginResponse response = refreshTokenUseCase.execute(request.getRefreshToken());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Realizar logout", description = "Endpoint para revogar acesso a uma sessão")
    @PostMapping("logout")
    public ResponseEntity<MessageResponse> logout(@Valid @RequestBody LogoutRequest request){
        logoutUseCase.execute(request.getRefreshToken());
        return ResponseEntity.ok(new MessageResponse("Logout realizado com sucesso"));
    }

    @Operation(summary = "Realizar logout em todoas as sessões", description = "Endpoint para revogar acesso a todas as sessões")
    @PostMapping("logout/all")
    public ResponseEntity<MessageResponse> logoutAll(@Valid @RequestBody LogoutRequest request){
        logoutAllAccountUseCase.execute(request.getRefreshToken());
        return ResponseEntity.ok(new MessageResponse("Logout realizado com sucesso"));
    }
}
