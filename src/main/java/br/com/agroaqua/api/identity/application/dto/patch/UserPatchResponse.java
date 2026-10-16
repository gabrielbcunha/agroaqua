package br.com.agroaqua.api.identity.application.dto.patch;

import br.com.agroaqua.api.shared.domain.Role;

public record UserPatchResponse(
        String email,
        Role role
) { }
