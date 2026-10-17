package br.com.agroaqua.api.telemetry.application.usecase.sensor;

import br.com.agroaqua.api.telemetry.application.dto.sensor.get.SensorGetResponse;
import br.com.agroaqua.api.telemetry.application.dto.sensor.patch.SensorPatchRequest;
import br.com.agroaqua.api.telemetry.domain.sensor.Sensor;
import br.com.agroaqua.api.telemetry.domain.sensor.SensorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatchSensorUseCase {

    private final SensorRepository sensorRepository;

    public PatchSensorUseCase(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public SensorGetResponse execute(Long id, SensorPatchRequest request) {

        if (id == null) {
            throw new IllegalArgumentException("id is required");
        }

        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("sensor not found with id " + id));

        if (request.code() != null && !request.code().trim().isEmpty()) {
            sensor.changeCode(request.code());
        }

        if (request.plotId() != null) {
            sensor.changePlotId(request.plotId());
        }

        Sensor patchedSensor = sensorRepository.save(sensor);
        return SensorGetResponse.fromDomain(patchedSensor);
    }
}
