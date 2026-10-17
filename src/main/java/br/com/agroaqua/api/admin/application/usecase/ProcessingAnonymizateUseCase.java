package br.com.agroaqua.api.admin.application.usecase;

import br.com.agroaqua.api.shared.domain.event.AnonymizeUserEmployeeEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProcessingAnonymizateUseCase {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final AuthenticationManager authenticationManager;


    public ProcessingAnonymizateUseCase(ApplicationEventPublisher applicationEventPublisher, AuthenticationManager authenticationManager) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    public void execute(AnonymizeUserEmployeeEvent request) {
        try {
            UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(request.emailConfirmation(), request.passwordConfirmation());
            Authentication authentication = authenticationManager.authenticate(login);

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
