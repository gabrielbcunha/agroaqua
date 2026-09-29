package br.com.agroaqua.api.telemetry.application.dto.measurement.get;

import br.com.agroaqua.api.telemetry.domain.measurement.Measurement;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MeasurementGetResponse(
        Long id,
        Long sensorId,
        BigDecimal humidity,
        LocalDateTime measuredAt
) {
    public static MeasurementGetResponse fromDomain(Measurement measurement) {
        return new MeasurementGetResponse(
                measurement.getId(),
                measurement.getSensorId(),
                measurement.getHumidity(),
                measurement.getMeasuredAt()
        );
    }
}
