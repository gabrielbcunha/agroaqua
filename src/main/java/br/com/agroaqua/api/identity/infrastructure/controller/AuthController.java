package br.com.agroaqua.api.identity.infrastructure.controller;

import br.com.agroaqua.api.identity.application.dto.get.UserGetResponse;
import br.com.agroaqua.api.identity.application.dto.login.LoginRequest;
import br.com.agroaqua.api.identity.application.dto.login.LoginResponse;
import br.com.agroaqua.api.identity.application.dto.patch.UserPatchRequest;
import br.com.agroaqua.api.identity.application.usecase.PatchUserUseCase;
import br.com.agroaqua.api.identity.application.usecase.UserLoginUseCase;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserLoginUseCase userLoginUseCase;
    private final PatchUserUseCase patchUserUseCase;

    public AuthController(UserLoginUseCase userLoginUseCase, PatchUserUseCase patchUserUseCase) {
        this.userLoginUseCase = userLoginUseCase;
        this.patchUserUseCase = patchUserUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse token = userLoginUseCase.execute(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @PreAuthorize("hasAnyAuthority('EMPLOYEE_MANAGER', 'EMPLOYEE_WORKER', 'ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<UserGetResponse> updateCredentials(
            @PathVariable Long id,
            @Valid @RequestBody UserPatchRequest patchRequest
    ) throws BadRequestException, AccessDeniedException {
        UserGetResponse response = patchUserUseCase.execute(id, patchRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
