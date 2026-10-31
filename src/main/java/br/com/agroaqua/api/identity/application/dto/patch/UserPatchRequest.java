package br.com.agroaqua.api.identity.application.dto.patch;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserPatchRequest(

        @Email(message = "The email format is invalid")
        String email,

        @Size(min=8, message = "The password must have at least 6 characters long")
        String password,

        @NotBlank(message = "The password field must be filled in")
        @Size(min=8, message = "The password must have at least 8 characters long")
        String currentPassword
) { }