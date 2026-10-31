package br.com.agroaqua.api.identity.application.dto.findbyemail;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserFindByEmailRequest(

        @NotBlank(message = "The email field must be filled in.")
        @Email(message = "The email format is invalid")
        String email
){ }
