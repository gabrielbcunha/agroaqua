package br.com.agroaqua.api.operational.application.usecase;

import br.com.agroaqua.api.operational.application.dto.get.EmployeeGetResponse;
import br.com.agroaqua.api.operational.application.dto.patch.EmployeePatchRequest;
import br.com.agroaqua.api.operational.domain.employee.Employee;
import br.com.agroaqua.api.operational.domain.employee.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatchEmployeeUseCase {

    private final EmployeeRepository employeeRepository;

    public PatchEmployeeUseCase(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public EmployeeGetResponse execute(Long id, EmployeePatchRequest request) {

        if (id == null) {
            throw new IllegalArgumentException("Id is required");
        }

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id " + id));

        if (request.name() != null && !request.name().trim().isEmpty()) {
            employee.changeName(request.name());
        }

        Employee patchedEmployee = employeeRepository.save(employee);
        return EmployeeGetResponse.fromDomain(patchedEmployee);
    }
}
