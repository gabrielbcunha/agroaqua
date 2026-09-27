package br.com.agroaqua.api.telemetry.application.usecase.measurement;

import br.com.agroaqua.api.telemetry.application.dto.measurement.create.MeasurementCreateRequest;
import br.com.agroaqua.api.telemetry.application.dto.measurement.create.MeasurementCreateResponse;
import br.com.agroaqua.api.telemetry.domain.measurement.Measurement;
import br.com.agroaqua.api.telemetry.domain.measurement.MeasurementRepository;
import br.com.agroaqua.api.telemetry.domain.sensor.Sensor;
import br.com.agroaqua.api.telemetry.domain.sensor.SensorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RegisterNewMeasurementUseCase {

    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterNewMeasurementUseCase(MeasurementRepository measurementRepository, SensorRepository sensorRepository, PasswordEncoder passwordEncoder) {
        this.measurementRepository = measurementRepository;
        this.sensorRepository = sensorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public MeasurementCreateResponse execute(MeasurementCreateRequest request, String apiKey) {
        Sensor sensor = sensorRepository.findById(request.sensorId())
                .orElseThrow(() -> new IllegalArgumentException("Sensor id not found"));
       if (!sensor.isActive()) {
           throw new IllegalArgumentException("Sensor is not active");
       }
       if (apiKey == null || !passwordEncoder.matches(apiKey, sensor.getApiKeyHash())) {
           throw new org.springframework.security.access.AccessDeniedException("Invalid sensor api key");
       }

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