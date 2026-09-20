package br.com.agroaqua.api.operational.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeSpringDataRepository extends JpaRepository<EmployeeJpaEntity, Long> {
}