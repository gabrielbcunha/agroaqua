package br.com.agroaqua.api.operational.application.usecase;

import br.com.agroaqua.api.operational.application.dto.EmployeeCreateRequest;
import br.com.agroaqua.api.operational.domain.employee.Employee;
import br.com.agroaqua.api.operational.domain.employee.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterNewEmployeeUseCase {

    private final EmployeeRepository employeeRepository;

    public RegisterNewEmployeeUseCase(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public void execute(EmployeeCreateRequest request) {
        Employee newEmployee = new Employee(request.userId(), request.name());
        employeeRepository.save(newEmployee);
    }
}