package br.com.agroaqua.api.telemetry.domain.measurement;

import java.util.List;
import java.util.Optional;

public interface MeasurementRepository {

    Measurement save(Measurement measurement);

    Optional<Measurement> findById(Long id);

    List<Measurement> findAll();

    List<Measurement> findAllBySensorId(Long sensorId);

    List<Measurement> findBySensorCode(String code);
}