package br.com.agroaqua.api.management.infrastructure.persistence.handling;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HandlingSpringDataRepository extends JpaRepository<HandlingJpaEntity, Long> {
}
