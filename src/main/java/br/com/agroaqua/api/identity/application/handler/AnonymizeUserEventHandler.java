package br.com.agroaqua.api.identity.application.handler;

import br.com.agroaqua.api.shared.domain.event.AnonymizeUserEmployeeEvent;
import br.com.agroaqua.api.identity.application.usecase.AnonymizeUserUseCase;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AnonymizeUserEventHandler {

    private final AnonymizeUserUseCase anonymizeUserUseCase;

    public AnonymizeUserEventHandler(AnonymizeUserUseCase anonymizeUserUseCase) {
        this.anonymizeUserUseCase = anonymizeUserUseCase;
    }

    @EventListener
    public void on(AnonymizeUserEmployeeEvent event) {
        anonymizeUserUseCase.execute(event.userId());
    }
}
