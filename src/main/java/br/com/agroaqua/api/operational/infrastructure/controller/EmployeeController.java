package br.com.agroaqua.api.operational.infrastructure.controller;

import br.com.agroaqua.api.operational.application.dto.get.EmployeeGetResponse;
import br.com.agroaqua.api.operational.application.dto.patch.EmployeePatchRequest;
import br.com.agroaqua.api.operational.application.usecase.FindEmployeeUseCase;
import br.com.agroaqua.api.operational.application.usecase.PatchEmployeeUseCase;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<Slice<EmployeeGetResponse>> findAllEmployees(Pageable pageable) {
        Slice<EmployeeGetResponse> response = findEmployeeUseCase.findAll(pageable);
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
    ) {
        EmployeeGetResponse response = patchEmployeeUseCase.execute(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
