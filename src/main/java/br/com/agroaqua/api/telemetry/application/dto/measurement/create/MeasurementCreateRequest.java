package br.com.agroaqua.api.telemetry.application.dto.measurement.create;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MeasurementCreateRequest(

        @NotNull(message = "The sensor ID field must be filled in")
        Long sensorId,

        @NotNull(message = "The humidity field must be filled in")
        @DecimalMin(value = "0.00", inclusive = false)
        @DecimalMax(value = "100.00", inclusive = true)
        @Digits(integer = 3, fraction = 2)
        BigDecimal humidity,

        LocalDateTime measuredAt
) {}
