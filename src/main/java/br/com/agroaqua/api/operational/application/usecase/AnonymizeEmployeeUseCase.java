package br.com.agroaqua.api.operational.application.usecase;

import br.com.agroaqua.api.operational.application.dto.anonymize.EmployeeAnonymizeResponse;
import br.com.agroaqua.api.operational.domain.employee.Employee;
import br.com.agroaqua.api.operational.domain.employee.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnonymizeEmployeeUseCase {

    private final EmployeeRepository employeeRepository;

    public AnonymizeEmployeeUseCase(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public EmployeeAnonymizeResponse execute (Long userId) {

        if (userId == null) {
            throw new IllegalArgumentException("userId cannot be null");
        }

        Employee employee =  employeeRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + userId));

        String anonymizedName = "Anonymized Employee with userId: " + userId;

        employee.anonymizeEmployee(anonymizedName);
        Employee anonymizedEmployee = employeeRepository.save(employee);
        return EmployeeAnonymizeResponse.fromDomain(anonymizedEmployee);
    }

}
