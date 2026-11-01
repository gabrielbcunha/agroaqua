package br.com.agroaqua.api.management.infrastructure.persistence.handling;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HandlingSpringDataRepository extends JpaRepository<HandlingJpaEntity, Long> {

    Slice<HandlingJpaEntity> findByEmployeeId(Long employeeId, Pageable pageable);

    Slice<HandlingJpaEntity> findByPlotId(Long plotId, Pageable pageable);
}
