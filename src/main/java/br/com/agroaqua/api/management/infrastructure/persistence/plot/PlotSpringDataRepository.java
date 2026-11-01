package br.com.agroaqua.api.management.infrastructure.persistence.plot;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlotSpringDataRepository extends JpaRepository<PlotJpaEntity, Long> {

    Optional<PlotJpaEntity> findByCode(String code);

    Slice<PlotJpaEntity> findByCropId(Long cropId, Pageable pageable);
}
