package br.com.agroaqua.api.operational.application.usecase;

import br.com.agroaqua.api.operational.application.dto.get.EmployeeGetResponse;
import br.com.agroaqua.api.operational.application.dto.patch.EmployeePatchRequest;
import br.com.agroaqua.api.operational.domain.employee.Employee;
import br.com.agroaqua.api.operational.domain.employee.EmployeeRepository;
import br.com.agroaqua.api.shared.infrastructure.security.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;

@Service
public class PatchEmployeeUseCase {

    private final EmployeeRepository employeeRepository;
    private final SecurityUtils securityUtils;

    public PatchEmployeeUseCase(EmployeeRepository employeeRepository, SecurityUtils securityUtils) {
        this.employeeRepository = employeeRepository;
        this.securityUtils = securityUtils;
    }

    @Transactional
    public EmployeeGetResponse execute(Long id, EmployeePatchRequest request) throws AccessDeniedException {

        if (id == null) {
            throw new IllegalArgumentException("Id is required");
        }

        Long loggedUserId = securityUtils.getLoggedUserId();

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id " + id));

        if (!employee.getUserId().equals(loggedUserId)) {
            throw new AccessDeniedException("Access denied, you are not authorized to perform this action");
        }

        if (request.name() != null && !request.name().trim().isEmpty()) {
            employee.changeName(request.name());
        }

        Employee patchedEmployee = employeeRepository.save(employee);
        return EmployeeGetResponse.fromDomain(patchedEmployee);
    }
}
