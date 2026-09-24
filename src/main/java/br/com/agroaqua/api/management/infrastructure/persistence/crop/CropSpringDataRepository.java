package br.com.agroaqua.api.management.infrastructure.persistence.crop;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CropSpringDataRepository extends JpaRepository<CropJpaEntity, Long> {
}
