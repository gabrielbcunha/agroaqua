package br.com.agroaqua.api.admin.application.controller;

import br.com.agroaqua.api.admin.application.dto.off.AdminAnonymizeRequest;
import br.com.agroaqua.api.admin.application.usecase.ProcessingAnonymizationUseCase;
import br.com.agroaqua.api.shared.domain.event.AnonymizeUserEmployeeEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/api/admin/offboarding")
public class AdminOffBoardingController {

    private final ProcessingAnonymizationUseCase processingAnonymizationUseCase;

    public AdminOffBoardingController(ProcessingAnonymizationUseCase processingAnonymizationUseCase) {
        this.processingAnonymizationUseCase = processingAnonymizationUseCase;
    }

    @DeleteMapping("/{userId}")
    ResponseEntity<Void> anonymizeUserEmployee(@PathVariable Long userId, @RequestBody AdminAnonymizeRequest request) throws AccessDeniedException {
        AnonymizeUserEmployeeEvent eventRequest = new AnonymizeUserEmployeeEvent(
                request.emailConfirmation(),
                request.passwordConfirmation(),
                userId
        );

        processingAnonymizationUseCase.execute(eventRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
