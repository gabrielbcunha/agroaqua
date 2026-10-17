package br.com.agroaqua.api.admin.application.controller;

import br.com.agroaqua.api.admin.application.dto.off.AdminAnonymizeRequest;
import br.com.agroaqua.api.admin.application.usecase.ProcessingAnonymizateUseCase;
import br.com.agroaqua.api.shared.domain.event.AnonymizeUserEmployeeEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/offboarding")
public class AdminOffBoardingController {

    private final ProcessingAnonymizateUseCase processingAnonymizateUseCase;

    public AdminOffBoardingController(ProcessingAnonymizateUseCase processingAnonymizateUseCase) {
        this.processingAnonymizateUseCase = processingAnonymizateUseCase;
    }

    @DeleteMapping("/{userId}")
    ResponseEntity<Void> anonymizeUserEmployee(@PathVariable Long userId, @RequestBody AdminAnonymizeRequest request) {
        AnonymizeUserEmployeeEvent eventRequest = new AnonymizeUserEmployeeEvent(
                request.emailConfirmation(),
                request.passwordConfirmation(),
                userId
        );

        processingAnonymizateUseCase.execute(eventRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
