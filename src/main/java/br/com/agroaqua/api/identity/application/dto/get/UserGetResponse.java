package br.com.agroaqua.api.identity.application.dto.get;

import br.com.agroaqua.api.identity.domain.user.User;
import br.com.agroaqua.api.shared.domain.Role;

public record UserGetResponse(
        Long id,
        String email,
        Role role
) {
    public static UserGetResponse fromDomain(User user) {
        return new UserGetResponse(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }
}
