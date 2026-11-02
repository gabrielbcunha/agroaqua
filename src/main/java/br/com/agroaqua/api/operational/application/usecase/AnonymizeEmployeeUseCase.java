package br.com.agroaqua.api.operational.application.usecase;

import br.com.agroaqua.api.operational.domain.employee.Employee;
import br.com.agroaqua.api.operational.domain.employee.EmployeeRepository;
import br.com.agroaqua.api.shared.domain.exception.DomainValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnonymizeEmployeeUseCase {

    private final EmployeeRepository employeeRepository;

    public AnonymizeEmployeeUseCase(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public void execute (Long userId) {

        if (userId == null) {
            throw new DomainValidationException("userId cannot be null");
        }

        Employee employee =  employeeRepository.findByUserId(userId)
                .orElseThrow(() -> new DomainValidationException("Employee not found with id: " + userId));

        String anonymizedName = "Anonymized Employee with userId: " + userId;

        employee.anonymizeEmployee(anonymizedName);
        employeeRepository.save(employee);
    }

}
