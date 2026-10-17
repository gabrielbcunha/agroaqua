package br.com.agroaqua.api.operational.domain.employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    Employee save(Employee employee);

    Optional<Employee> findById(Long id);

    List<Employee> findAll();
}