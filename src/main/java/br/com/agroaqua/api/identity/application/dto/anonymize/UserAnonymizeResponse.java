package br.com.agroaqua.api.identity.application.dto.anonymize;

import br.com.agroaqua.api.identity.domain.user.User;
import br.com.agroaqua.api.shared.domain.Role;

public record UserAnonymizeResponse (
        Long id,
        String email,
        Role role
) {
    public static UserAnonymizeResponse fromDomain(User user){

        return new UserAnonymizeResponse(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }
}

