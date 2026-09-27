package br.com.agroaqua.api.telemetry.application.usecase.sensor;

import br.com.agroaqua.api.telemetry.application.dto.sensor.create.SensorCreateRequest;
import br.com.agroaqua.api.telemetry.application.dto.sensor.create.SensorCreateResponse;
import br.com.agroaqua.api.telemetry.domain.sensor.Sensor;
import br.com.agroaqua.api.telemetry.domain.sensor.SensorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterNewSensorUseCase {

    private final SensorRepository sensorRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterNewSensorUseCase(SensorRepository sensorRepository, PasswordEncoder passwordEncoder) {
        this.sensorRepository = sensorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public SensorCreateResponse execute(SensorCreateRequest request) {
        String rawApiKey = java.util.UUID.randomUUID().toString();
        String apiKeyHash = passwordEncoder.encode(rawApiKey);

        Sensor newSensor = new Sensor(
                request.code(),
                request.plotId(),
                request.active(),
                apiKeyHash
        );
        Sensor savedSensor = sensorRepository.save(newSensor);
        return new SensorCreateResponse(
                savedSensor.getId(),
                savedSensor.getCode(),
                savedSensor.getPlotId(),
                savedSensor.isActive(),
                rawApiKey
        );
    }

}