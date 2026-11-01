package br.com.agroaqua.api.telemetry.domain.sensor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface SensorRepository {

    Sensor save(Sensor sensor);

    Optional<Sensor> findById(Long id);

    Slice<Sensor> findAll(Pageable pageable);

    Optional<Sensor> findByCode(String sensorCode);
}