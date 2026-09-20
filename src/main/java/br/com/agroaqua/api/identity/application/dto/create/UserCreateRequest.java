package br.com.agroaqua.api.identity.application.dto.create;

import br.com.agroaqua.api.shared.domain.Role;

public record UserCreateRequest (String email, String password, Role role) {
}
