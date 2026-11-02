package br.com.agroaqua.api.admin.infrastructure.controller;

import br.com.agroaqua.api.admin.application.dto.off.AdminAnonymizeRequest;
import br.com.agroaqua.api.admin.application.usecase.ProcessingAnonymizationUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin/offboarding")
public class AdminOffBoardingController {

    private final ProcessingAnonymizationUseCase processingAnonymizationUseCase;

    public AdminOffBoardingController(ProcessingAnonymizationUseCase processingAnonymizationUseCase) {
        this.processingAnonymizationUseCase = processingAnonymizationUseCase;
    }

    @PostMapping("/{userId}/anonymize")
    ResponseEntity<Void> anonymizeUserEmployee(
            @PathVariable Long userId,
            @Valid @RequestBody AdminAnonymizeRequest requestConfirmation) {
        processingAnonymizationUseCase.execute(userId, requestConfirmation);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
