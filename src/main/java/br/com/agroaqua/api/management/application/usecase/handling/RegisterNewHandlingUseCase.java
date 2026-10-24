package br.com.agroaqua.api.management.application.usecase.handling;

import br.com.agroaqua.api.management.application.dto.handling.create.HandlingCreateRequest;
import br.com.agroaqua.api.management.application.dto.handling.create.HandlingCreateResponse;
import br.com.agroaqua.api.management.domain.handling.Handling;
import br.com.agroaqua.api.management.domain.handling.HandlingRepository;
import br.com.agroaqua.api.operational.domain.employee.Employee;
import br.com.agroaqua.api.operational.domain.employee.EmployeeRepository;
import br.com.agroaqua.api.shared.infrastructure.security.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;

@Service
public class RegisterNewHandlingUseCase {

    private final HandlingRepository handlingRepository;
    private final SecurityUtils securityUtils;
    private final EmployeeRepository employeeRepository;

    public RegisterNewHandlingUseCase(HandlingRepository handlingRepository, SecurityUtils securityUtils, EmployeeRepository employeeRepository) {
        this.handlingRepository = handlingRepository;
        this.securityUtils = securityUtils;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public HandlingCreateResponse execute(HandlingCreateRequest request) throws AccessDeniedException {
        Handling newHandling = new Handling(
                request.employeeId(),
                request.plotId(),
                request.category(),
                request.description(),
                request.startDateTime(),
                request.endDateTime()
                );

        Long loggedUserId = securityUtils.getLoggedUserId();

        Employee loggedEmployee = employeeRepository.findByUserId(loggedUserId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id " + loggedUserId));

        if (!loggedEmployee.getId().equals(request.employeeId())) {
            throw new AccessDeniedException("You are not allowed to perform this action");
        }

        Handling savedHandling = handlingRepository.save(newHandling);
        return new HandlingCreateResponse(
                savedHandling.getId(),
                savedHandling.getEmployeeId(),
                savedHandling.getPlotId(),
                savedHandling.getCategory(),
                savedHandling.getDescription(),
                savedHandling.getStartDateTime(),
                savedHandling.getEndDateTime()
        );
    }


}