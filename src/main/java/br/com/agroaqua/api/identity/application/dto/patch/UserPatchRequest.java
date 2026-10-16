package br.com.agroaqua.api.identity.application.dto.patch;

public record UserPatchRequest(
        String email,
        String password,
        String currentPassword
) { }
