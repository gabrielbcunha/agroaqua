package br.com.agroaqua.api.telemetry.infrastructure.persistence.sensor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorSpringDataRepository extends JpaRepository<SensorJpaEntity, Long> {
}
