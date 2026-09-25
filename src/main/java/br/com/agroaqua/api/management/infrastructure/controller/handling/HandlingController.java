package br.com.agroaqua.api.management.infrastructure.controller.handling;

import br.com.agroaqua.api.management.application.dto.handling.create.HandlingCreateRequest;
import br.com.agroaqua.api.management.application.dto.handling.create.HandlingCreateResponse;
import br.com.agroaqua.api.management.application.usecase.handling.RegisterNewHandlingUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/handling")
public class HandlingController {

    private final RegisterNewHandlingUseCase registerNewHandlingUseCase;

    public HandlingController(RegisterNewHandlingUseCase registerNewHandlingUseCase) {
        this.registerNewHandlingUseCase = registerNewHandlingUseCase;
    }

    @PostMapping()
    public ResponseEntity<HandlingCreateResponse> registerHandling(@RequestBody HandlingCreateRequest request) {
        HandlingCreateResponse response = registerNewHandlingUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}