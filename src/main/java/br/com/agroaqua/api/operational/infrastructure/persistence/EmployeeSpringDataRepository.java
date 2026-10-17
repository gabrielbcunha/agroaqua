package br.com.agroaqua.api.operational.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeSpringDataRepository extends JpaRepository<EmployeeJpaEntity, Long> {

    Optional<EmployeeJpaEntity> findByUserId(Long userId);

}