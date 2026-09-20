package br.com.agroaqua.api.admin.application.dto;

import br.com.agroaqua.api.shared.domain.Role;

public record AdminOnboardingResponse(
        String name,
        String email,
        Long userId,
        Role role
) {}
