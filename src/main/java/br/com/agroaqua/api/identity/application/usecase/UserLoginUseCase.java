package br.com.agroaqua.api.identity.application.usecase;

import br.com.agroaqua.api.identity.application.dto.login.LoginRequest;
import br.com.agroaqua.api.identity.application.dto.login.LoginResponse;
import br.com.agroaqua.api.shared.infrastructure.security.JwtTokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserLoginUseCase {

    private final JwtTokenService jwtTokenService;
    private final AuthenticationManager authenticationManager;

    public UserLoginUseCase(JwtTokenService jwtTokenService, AuthenticationManager authenticationManager) {
        this.jwtTokenService = jwtTokenService;
        this.authenticationManager = authenticationManager;
    }

    public LoginResponse execute(LoginRequest request) {
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authentication = authenticationManager.authenticate(login);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtTokenService.generateToken(userDetails);
        return new LoginResponse(token);
    }

}
