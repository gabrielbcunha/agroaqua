package br.com.agroaqua.api.operational.infrastructure.controller;

import br.com.agroaqua.api.operational.application.dto.get.EmployeeGetResponse;
import br.com.agroaqua.api.operational.application.usecase.FindEmployeeUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final FindEmployeeUseCase findEmployeeUseCase;

    public EmployeeController(FindEmployeeUseCase findEmployeeUseCase) {
        this.findEmployeeUseCase = findEmployeeUseCase;
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
}
