package br.com.agroaqua.api.telemetry.infrastructure.persistence.measurement;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeasurementSpringDataRepository extends JpaRepository<MeasurementJpaEntity, Long> {
    Slice<MeasurementJpaEntity> findBySensorId(Long sensorId, Pageable pageable);
}
