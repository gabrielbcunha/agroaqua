package br.com.agroaqua.api.operational.infrastructure.persistence;

import br.com.agroaqua.api.operational.domain.employee.Employee;
import br.com.agroaqua.api.operational.domain.employee.EmployeeRepository;
import br.com.agroaqua.api.operational.infrastructure.mapper.EmployeeMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final EmployeeSpringDataRepository employeeSpringDataRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeRepositoryImpl(EmployeeSpringDataRepository employeeSpringDataRepository, EmployeeMapper employeeMapper) {
        this.employeeSpringDataRepository = employeeSpringDataRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public void save(Employee employee) {
        EmployeeJpaEntity jpaEntity = employeeMapper.toJpaEntity(employee);
        employeeSpringDataRepository.save(jpaEntity);
    }

    //Mudar
    @Override
    public Optional<Employee> findById(Long id) {
        return Optional.empty();
    }

    //Mudar
    @Override
    public List<Employee> findAllActiveEmployees() {
        return List.of();
    }
}