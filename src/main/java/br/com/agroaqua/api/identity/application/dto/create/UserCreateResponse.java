package br.com.agroaqua.api.identity.application.dto.create;

import br.com.agroaqua.api.shared.domain.Role;

public record UserCreateResponse (String userId, String email, Role role) {
}
