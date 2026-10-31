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
        Sensor sensor = sensorRepository.findById(request.sensorId()).orElse(null);

        boolean isAuthenticated = false;

        if (sensor != null && sensor.isActive() && apiKey != null) {
            isAuthenticated = passwordEncoder.matches(apiKey, sensor.getApiKeyHash());
        }

        if (!isAuthenticated) {
            throw new org.springframework.security.access.AccessDeniedException("Access denied: Invalid credentials or inactive sensor");
        }

        LocalDateTime receivedTime = request.measuredAt();
        if (request.measuredAt() == null) {
            receivedTime = LocalDateTime.now();
        } else if (request.measuredAt().isAfter(LocalDateTime.now())) {
            receivedTime = LocalDateTime.now();
        } else if (request.measuredAt().isBefore(LocalDateTime.now().minusDays(1))) {
            throw new IllegalArgumentException("The measurement time has expired");
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