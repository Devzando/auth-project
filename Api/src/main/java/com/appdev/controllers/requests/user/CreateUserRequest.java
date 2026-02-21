package com.appdev.controllers.requests.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest{
        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Formato de e-mail inválido")
        String email;

        @NotBlank(message = "O Nome obrigatório")
        @Size(min = 3, max = 60, message = "o nome deve ter no mínimo 3 caracteres e no máximo 60")
        String name;

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, max = 16, message = "A senha deve ter no mínimo 6 caracteres e no máximo 16")
        String password;

        @NotBlank(message = "A role é obrigatória")
        String role;
}
