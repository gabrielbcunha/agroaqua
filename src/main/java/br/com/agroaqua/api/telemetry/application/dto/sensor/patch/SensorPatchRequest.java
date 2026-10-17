package br.com.agroaqua.api.telemetry.application.dto.sensor.patch;

public record SensorPatchRequest(
    String code,
    Long plotId
) { }
