package br.com.agroaqua.api.management.infrastructure.persistence.plot;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlotSpringDataRepository extends JpaRepository<PlotJpaEntity, Long> {
}
