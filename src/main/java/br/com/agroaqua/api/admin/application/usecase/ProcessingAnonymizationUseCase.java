package br.com.agroaqua.api.admin.application.usecase;

import br.com.agroaqua.api.admin.application.dto.off.AdminAnonymizeRequest;
import br.com.agroaqua.api.shared.domain.event.AnonymizeUserEmployeeEvent;
import br.com.agroaqua.api.shared.domain.exception.DomainAuthorizationException;
import br.com.agroaqua.api.shared.domain.exception.DomainValidationException;
import br.com.agroaqua.api.shared.infrastructure.security.SecurityUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void execute(Long userId, AdminAnonymizeRequest requestConfirmation) {

        String loggedUsername = securityUtils.getLoggedUsername();
        Long loggedUserId = securityUtils.getLoggedUserId();

        if (!loggedUsername.equals(requestConfirmation.emailConfirmation())) {
            throw new DomainAuthorizationException("The confirmation email must match your logged account");
        }

        if (userId.equals(loggedUserId)) {
            throw new DomainValidationException("A Administrator cannot anonymize their own account");
        }

        if (userId == 1L) {
            throw new DomainValidationException("The primary administrator cannot be anonymized");
        }

        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(requestConfirmation.emailConfirmation(), requestConfirmation.passwordConfirmation());

        try {
            Authentication authentication = authenticationManager.authenticate(login);
        } catch (org.springframework.security.authentication.BadCredentialsException ex) {
            throw new DomainValidationException("The username or password is invalid");
        }

        AnonymizeUserEmployeeEvent event =  new AnonymizeUserEmployeeEvent(
                userId
        );
        applicationEventPublisher.publishEvent(event);

    }

}
