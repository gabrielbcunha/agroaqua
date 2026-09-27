package br.com.agroaqua.api.telemetry.application.dto.measurement.create;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MeasurementCreateResponse(
        Long id,
        Long sensorId,
        BigDecimal humidity,
        LocalDateTime measuredAt
) {}