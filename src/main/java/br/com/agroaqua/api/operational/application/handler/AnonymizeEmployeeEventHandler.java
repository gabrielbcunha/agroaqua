package br.com.agroaqua.api.operational.application.handler;

import br.com.agroaqua.api.shared.domain.event.AnonymizeUserEmployeeEvent;
import br.com.agroaqua.api.operational.application.usecase.AnonymizeEmployeeUseCase;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AnonymizeEmployeeEventHandler {

    private final AnonymizeEmployeeUseCase anonymizeEmployeeUseCase;

    public AnonymizeEmployeeEventHandler(AnonymizeEmployeeUseCase anonymizeEmployeeUseCase) {
        this.anonymizeEmployeeUseCase = anonymizeEmployeeUseCase;
    }

    @EventListener
    public void on(AnonymizeUserEmployeeEvent event) {
        anonymizeEmployeeUseCase.execute(event.userId());
    }
}
