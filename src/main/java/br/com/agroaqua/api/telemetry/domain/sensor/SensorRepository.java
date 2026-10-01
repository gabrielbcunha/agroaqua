package br.com.agroaqua.api.telemetry.domain.sensor;

import java.util.List;
import java.util.Optional;

public interface SensorRepository {

    Sensor save(Sensor sensor);

    Optional<Sensor> findById(Long id);

    List<Sensor> findAll();

    Optional<Sensor> findByCode(String sensorCode);
}