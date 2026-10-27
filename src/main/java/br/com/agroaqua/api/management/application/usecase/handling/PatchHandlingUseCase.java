package br.com.agroaqua.api.management.application.usecase.handling;

import br.com.agroaqua.api.management.application.dto.handling.get.HandlingGetResponse;
import br.com.agroaqua.api.management.application.dto.handling.patch.HandlingPatchRequest;
import br.com.agroaqua.api.management.domain.handling.Handling;
import br.com.agroaqua.api.management.domain.handling.HandlingRepository;
import br.com.agroaqua.api.operational.domain.employee.Employee;
import br.com.agroaqua.api.operational.domain.employee.EmployeeRepository;
import br.com.agroaqua.api.shared.infrastructure.security.JwtTokenService;
import br.com.agroaqua.api.shared.infrastructure.security.SecurityUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;

@Service
public class PatchHandlingUseCase {

    private final HandlingRepository handlingRepository;
    private final SecurityUtils securityUtils;
    private final EmployeeRepository employeeRepository;

    public PatchHandlingUseCase(HandlingRepository handlingRepository, SecurityUtils securityUtils, EmployeeRepository employeeRepository) {
        this.handlingRepository = handlingRepository;
        this.securityUtils = securityUtils;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public HandlingGetResponse execute(Long id, HandlingPatchRequest request) throws AccessDeniedException {

        if (id == null) {
            throw new IllegalArgumentException("Id is required");
        }

        Long loggedUserId = securityUtils.getLoggedUserId();

        Employee loggedEmployee = employeeRepository.findByUserId(loggedUserId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with user id: " + loggedUserId));

        Handling handling = handlingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Handling not found with id " + id));

        if (!handling.getEmployeeId().equals(loggedEmployee.getId())) {
            throw new AccessDeniedException("You are not allowed to perform this action");
        }

        if (request.employeeId() != null && !request.employeeId().equals(loggedEmployee.getId())) {
            throw new AccessDeniedException("You are not allowed to perform this action");
        }

        if (request.employeeId() != null) {
            handling.changeEmployeeId(request.employeeId());
        }

        if (request.plotId() != null) {
            handling.changePlotId(request.plotId());
        }

        if (request.category() != null) {
            handling.changeCategory(request.category());
        }

        if (request.description() != null && !request.description().trim().isEmpty()) {
            handling.changeDescription(request.description());
        }

        handling.changePeriod(request.startDateTime(), request.endDateTime());

        Handling patchedHandling = handlingRepository.save(handling);
        return HandlingGetResponse.fromDomain(patchedHandling);
    }
}