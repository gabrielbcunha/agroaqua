package br.com.agroaqua.api.telemetry.infrastructure.persistence.measurement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementSpringDataRepository extends JpaRepository<MeasurementJpaEntity, Long> {
}
