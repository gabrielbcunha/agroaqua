package br.com.agroaqua.api.telemetry.application.dto.sensor.get;

import br.com.agroaqua.api.telemetry.domain.sensor.Sensor;

public record SensorGetResponse(
        Long id,
        String code,
        Long plotId,
        Boolean active
) {
    public static SensorGetResponse fromDomain(Sensor sensor) {
        return new SensorGetResponse(
                sensor.getId(),
                sensor.getCode(),
                sensor.getPlotId(),
                sensor.isActive()
        );
    }

}