package br.com.agroaqua.api.telemetry.application.usecase.sensor;

import br.com.agroaqua.api.telemetry.application.dto.sensor.get.SensorGetResponse;
import br.com.agroaqua.api.telemetry.domain.sensor.Sensor;
import br.com.agroaqua.api.telemetry.domain.sensor.SensorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindSensorUseCase {

    private final SensorRepository sensorRepository;

    public FindSensorUseCase(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public SensorGetResponse findById(Long id){
        Sensor sensor =  sensorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sensor not found with ID: " + id));
        return SensorGetResponse.fromDomain(sensor);
    }

    public List<SensorGetResponse> findAll(){
        return sensorRepository.findAll()
                .stream()
                .map(SensorGetResponse::fromDomain)
                .toList();
    }
}