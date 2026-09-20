package br.com.agroaqua.api.operational.domain.employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    void save(Employee employee);

    Optional<Employee> findById(Long id);

    List<Employee> findAllActiveEmployees();
}