package br.com.agroaqua.api.telemetry.application.usecase.sensor;

import br.com.agroaqua.api.telemetry.application.dto.sensor.get.SensorGetResponse;
import br.com.agroaqua.api.telemetry.domain.sensor.Sensor;
import br.com.agroaqua.api.telemetry.domain.sensor.SensorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeactivateSensorUseCase {

    private final SensorRepository sensorRepository;

    public DeactivateSensorUseCase(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public SensorGetResponse execute(Long id){
        if (id == null){
            throw new IllegalArgumentException("Id cannot be null");
        }

        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sensor not found with id " + id));

        sensor.deactivate();
        Sensor deactivateSensor = sensorRepository.save(sensor);
        return SensorGetResponse.fromDomain(deactivateSensor);
    }
}