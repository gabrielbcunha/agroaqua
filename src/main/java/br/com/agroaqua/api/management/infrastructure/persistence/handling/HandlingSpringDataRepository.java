package br.com.agroaqua.api.management.infrastructure.persistence.handling;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HandlingSpringDataRepository extends JpaRepository<HandlingJpaEntity, Long> {

    List<HandlingJpaEntity> findByEmployeeId(Long employeeId);

    List<HandlingJpaEntity> findByPlotId(Long plotId);
}
