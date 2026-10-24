package br.com.agroaqua.api.admin.application.usecase;

import br.com.agroaqua.api.shared.domain.event.AnonymizeUserEmployeeEvent;
import br.com.agroaqua.api.shared.infrastructure.security.SecurityUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;

@Service
public class ProcessingAnonymizationUseCase {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final AuthenticationManager authenticationManager;
    private final SecurityUtils securityUtils;

    public ProcessingAnonymizationUseCase(ApplicationEventPublisher applicationEventPublisher, AuthenticationManager authenticationManager, SecurityUtils securityUtils) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.authenticationManager = authenticationManager;
        this.securityUtils = securityUtils;
    }

    @Transactional
    public void execute(AnonymizeUserEmployeeEvent request) throws AccessDeniedException {

        String loggedUsername = securityUtils.getLoggedUsername();
        Long loggedUserId = securityUtils.getLoggedUserId();

        if (!loggedUsername.equals(request.emailConfirmation())) {
            throw new BadCredentialsException("The confirmation email must match your logged account");
        }

        if (request.userId().equals(loggedUserId)) {
            throw new IllegalArgumentException("A Administrator cannot anonymize their own account");
        }

        if (request.userId() == 1L) {
            throw new IllegalArgumentException("The primary administrator cannot be anonymized");
        }

        try {
            UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(request.emailConfirmation(), request.passwordConfirmation());
            Authentication authentication = authenticationManager.authenticate(login);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            AnonymizeUserEmployeeEvent event =  new AnonymizeUserEmployeeEvent(
                    "",
                    "",
                    request.userId()
            );
            applicationEventPublisher.publishEvent(event);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid email or password");
        }
    }

}
