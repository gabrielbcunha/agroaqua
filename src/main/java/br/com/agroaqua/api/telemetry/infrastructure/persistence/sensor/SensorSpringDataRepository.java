package br.com.agroaqua.api.telemetry.infrastructure.persistence.sensor;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SensorSpringDataRepository extends JpaRepository<SensorJpaEntity, Long> {
    Optional<SensorJpaEntity> findByCode(String sensorCode);
}
