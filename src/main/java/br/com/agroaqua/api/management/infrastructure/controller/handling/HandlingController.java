package br.com.agroaqua.api.management.infrastructure.controller.handling;

import br.com.agroaqua.api.management.application.dto.handling.create.HandlingCreateRequest;
import br.com.agroaqua.api.management.application.dto.handling.create.HandlingCreateResponse;
import br.com.agroaqua.api.management.application.dto.handling.get.HandlingGetResponse;
import br.com.agroaqua.api.management.application.usecase.handling.FindHandlingUseCase;
import br.com.agroaqua.api.management.application.usecase.handling.RegisterNewHandlingUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/handling")
public class HandlingController {

    private final RegisterNewHandlingUseCase registerNewHandlingUseCase;
    private final FindHandlingUseCase findHandlingUseCase;

    public HandlingController(RegisterNewHandlingUseCase registerNewHandlingUseCase, FindHandlingUseCase findHandlingUseCase) {
        this.registerNewHandlingUseCase = registerNewHandlingUseCase;
        this.findHandlingUseCase = findHandlingUseCase;
    }

    @PostMapping()
    public ResponseEntity<HandlingCreateResponse> registerHandling(@RequestBody HandlingCreateRequest request) {
        HandlingCreateResponse response = registerNewHandlingUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping()
    public ResponseEntity<List<HandlingGetResponse>> findAllHandlings() {
        List<HandlingGetResponse> response = findHandlingUseCase.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HandlingGetResponse> findHandlingById(@PathVariable Long id) {
        HandlingGetResponse response = findHandlingUseCase.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<HandlingGetResponse>> findHandlingByEmployeeId(@PathVariable Long employeeId) {
        List<HandlingGetResponse> response = findHandlingUseCase.findByEmployeeId(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/plot/{plotId}")
    public ResponseEntity<List<HandlingGetResponse>> findHandlingByPlotId(@PathVariable Long plotId) {
        List<HandlingGetResponse> response = findHandlingUseCase.findByPlotId(plotId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}