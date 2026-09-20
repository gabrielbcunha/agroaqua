package br.com.agroaqua.api.admin.application.dto;

import br.com.agroaqua.api.shared.domain.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AdminOnboardingRequest (

        @NotBlank(message = "The email field must be filled in.")
        @Email(message = "The email format is invalid")
        String email,

        @NotBlank(message = "The password field must be filled in")
        @Size(min=6, message = "The password must have at least 6 characters long")
        String password,

        @NotBlank(message = "The name field must be filled in")
        String name,

        @NotNull(message = "The role field must be filled in")
        Role role
){}
