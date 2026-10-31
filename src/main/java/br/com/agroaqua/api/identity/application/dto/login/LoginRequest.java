package br.com.agroaqua.api.identity.application.dto.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest (

        @NotBlank(message = "The email field must be filled in.")
        @Email(message = "The email format is invalid")
        String email,

        @NotBlank(message = "The password field must be filled in")
        @Size(min=6, message = "The password must have at least 6 characters long")
        String password
){ }