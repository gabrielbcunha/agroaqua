package br.com.agroaqua.api.telemetry.domain.measurement;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface MeasurementRepository {

    Measurement save(Measurement measurement);

    Optional<Measurement> findById(Long id);

    Slice<Measurement> findAll(Pageable pageable);

    Slice<Measurement> findAllBySensorId(Long sensorId, Pageable pageable);

    Slice<Measurement> findBySensorCode(String code, Pageable pageable);
}