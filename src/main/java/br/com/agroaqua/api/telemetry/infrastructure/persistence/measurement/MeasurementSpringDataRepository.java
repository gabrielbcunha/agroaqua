package br.com.agroaqua.api.telemetry.infrastructure.persistence.measurement;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeasurementSpringDataRepository extends JpaRepository<MeasurementJpaEntity, Long> {
    List<MeasurementJpaEntity> findBySensorId(Long sensorId);
}
