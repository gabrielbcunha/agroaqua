package br.com.agroaqua.api.operational.application.dto.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmployeeCreateRequest(

        @NotNull(message = "The user ID field must be filled in")
        Long userId,

        @NotBlank(message = "The name field must be filled in")
        String name
) { }