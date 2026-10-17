package br.com.agroaqua.api.shared.domain.event;

public record AnonymizeUserEmployeeEvent(
        String emailConfirmation,
        String passwordConfirmation,
        Long userId
) { }
