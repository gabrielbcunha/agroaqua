package br.com.agroaqua.api.admin.application.controller;

import br.com.agroaqua.api.admin.application.usecase.AdminOnboardingUseCase;
import br.com.agroaqua.api.admin.application.dto.AdminOnboardingRequest;
import br.com.agroaqua.api.admin.application.dto.AdminOnboardingResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/onboarding")
public class AdminOnboardingController {

    private final AdminOnboardingUseCase adminOnboardingUseCase;

    public AdminOnboardingController(AdminOnboardingUseCase adminOnboardingUseCase) {
        this.adminOnboardingUseCase = adminOnboardingUseCase;
    }

    @PostMapping("/employees")
    public ResponseEntity<AdminOnboardingResponse> registerEmployee(@Valid @RequestBody AdminOnboardingRequest request) {
        AdminOnboardingResponse response = adminOnboardingUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}