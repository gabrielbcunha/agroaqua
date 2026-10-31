package br.com.agroaqua.api.operational.infrastructure.controller;

import br.com.agroaqua.api.operational.application.dto.get.EmployeeGetResponse;
import br.com.agroaqua.api.operational.application.dto.patch.EmployeePatchRequest;
import br.com.agroaqua.api.operational.application.usecase.FindEmployeeUseCase;
import br.com.agroaqua.api.operational.application.usecase.PatchEmployeeUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final FindEmployeeUseCase findEmployeeUseCase;
    private final PatchEmployeeUseCase patchEmployeeUseCase;

    public EmployeeController(FindEmployeeUseCase findEmployeeUseCase, PatchEmployeeUseCase patchEmployeeUseCase) {
        this.findEmployeeUseCase = findEmployeeUseCase;
        this.patchEmployeeUseCase = patchEmployeeUseCase;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeGetResponse>> findAllEmployees() {
        List<EmployeeGetResponse> response = findEmployeeUseCase.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeGetResponse> findById (@PathVariable Long id) {
        EmployeeGetResponse response = findEmployeeUseCase.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeGetResponse> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeePatchRequest request
    ) throws AccessDeniedException {
        EmployeeGetResponse response = patchEmployeeUseCase.execute(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
