package br.com.agroaqua.api.telemetry.application.usecase.sensor;

import br.com.agroaqua.api.telemetry.application.dto.sensor.create.SensorCreateRequest;
import br.com.agroaqua.api.telemetry.application.dto.sensor.create.SensorCreateResponse;
import br.com.agroaqua.api.telemetry.domain.sensor.Sensor;
import br.com.agroaqua.api.telemetry.domain.sensor.SensorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterNewSensorUseCase {

    private final SensorRepository sensorRepository;

    public RegisterNewSensorUseCase(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public SensorCreateResponse execute(SensorCreateRequest request) {
        Sensor newSensor = new Sensor(
                request.code(),
                request.plotId(),
                request.active()
        );
        Sensor savedSensor = sensorRepository.save(newSensor);
        return new SensorCreateResponse(
                savedSensor.getId(),
                savedSensor.getCode(),
                savedSensor.getPlotId(),
                savedSensor.isActive()
        );
    }

}