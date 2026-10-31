package br.com.agroaqua.api.management.application.dto.handling.create;

import br.com.agroaqua.api.management.domain.handling.HandlingCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record HandlingCreateRequest(

        @NotNull(message = "The employee ID field must be filled in")
        Long employeeId,

        @NotNull(message = "The plot ID field must be filled in")
        Long plotId,

        @NotNull(message = "The category field must be filled in")
        HandlingCategory category,

        @NotBlank(message = "The description field must be filled in")
        @Size(max=200, message = "The description field must contain fewer than 200 characters.")
        String description,

        @NotNull(message = "The start date time field must be filled in")
        LocalDateTime startDateTime,

        @NotNull(message = "The end date time field must be filled in")
        LocalDateTime endDateTime
) { }