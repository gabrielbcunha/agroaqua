package br.com.agroaqua.api.telemetry.application.dto.sensor.create;

public record SensorCreateResponse(
        Long id,
        String code,
        Long plotId,
        boolean active
) {}