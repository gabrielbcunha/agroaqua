package br.com.agroaqua.api.telemetry.application.dto.sensor.create;

public record SensorCreateRequest(
        String code,
        Long plotId,
        boolean active
) {}
