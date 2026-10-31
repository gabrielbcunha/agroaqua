package br.com.agroaqua.api.admin.application.dto.off;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AdminAnonymizeRequest(

        @NotBlank(message = "The email confirmation field must be filled in.")
        @Email(message = "The email format is invalid")
        String emailConfirmation,

        @NotBlank(message = "The password confirmation field must be filled in.")
        String passwordConfirmation
){ }