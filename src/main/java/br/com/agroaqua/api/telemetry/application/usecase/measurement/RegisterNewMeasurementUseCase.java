package br.com.agroaqua.api.telemetry.application.usecase.measurement;

import br.com.agroaqua.api.telemetry.application.dto.measurement.create.MeasurementCreateRequest;
import br.com.agroaqua.api.telemetry.application.dto.measurement.create.MeasurementCreateResponse;
import br.com.agroaqua.api.telemetry.domain.measurement.Measurement;
import br.com.agroaqua.api.telemetry.domain.measurement.MeasurementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RegisterNewMeasurementUseCase {

    private final MeasurementRepository measurementRepository;

    public RegisterNewMeasurementUseCase(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    @Transactional
    public MeasurementCreateResponse execute(MeasurementCreateRequest request) {
        LocalDateTime receivedTime = request.measuredAt();
        if (request.measuredAt() == null) {
            receivedTime = LocalDateTime.now();
        }

        Measurement measurement = new Measurement(
                request.sensorId(),
                request.humidity(),
                receivedTime);
        Measurement savedMeasurement = measurementRepository.save(measurement);
        return new MeasurementCreateResponse(
                savedMeasurement.getId(),
                savedMeasurement.getSensorId(),
                savedMeasurement.getHumidity(),
                savedMeasurement.getMeasuredAt()
                );
    }
}