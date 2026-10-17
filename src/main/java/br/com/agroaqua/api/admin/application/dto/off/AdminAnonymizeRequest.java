package br.com.agroaqua.api.admin.application.dto.off;

public record AdminAnonymizeRequest(
        String emailConfirmation,
        String passwordConfirmation
){ }