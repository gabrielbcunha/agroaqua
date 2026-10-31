package br.com.agroaqua.api.operational.domain.employee;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface EmployeeRepository {

    Employee save(Employee employee);

    Optional<Employee> findById(Long id);

    Optional<Employee> findByUserId(Long userId);
    Slice<Employee> findAll(Pageable pageable);
}