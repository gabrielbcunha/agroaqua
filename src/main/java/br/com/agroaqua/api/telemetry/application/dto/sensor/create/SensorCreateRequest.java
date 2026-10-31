package br.com.agroaqua.api.telemetry.application.dto.sensor.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SensorCreateRequest(

        @NotBlank(message = "The code field must be filled in")
        String code,

        @NotNull(message = "The plot ID field must be filled in")
        Long plotId,

        @NotNull(message = "The active field must be filled in")
        boolean active
) {}
