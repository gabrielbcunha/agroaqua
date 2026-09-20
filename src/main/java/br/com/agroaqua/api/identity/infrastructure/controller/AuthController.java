package br.com.agroaqua.api.identity.infrastructure.controller;

import br.com.agroaqua.api.identity.application.dto.login.LoginRequest;
import br.com.agroaqua.api.identity.application.dto.login.LoginResponse;
import br.com.agroaqua.api.identity.application.usecase.UserLoginUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserLoginUseCase userLoginUseCase;

    public AuthController(UserLoginUseCase userLoginUseCase) {
        this.userLoginUseCase = userLoginUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse token = userLoginUseCase.execute(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

}
