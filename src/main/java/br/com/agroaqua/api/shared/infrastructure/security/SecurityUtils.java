package br.com.agroaqua.api.shared.infrastructure.security;

import br.com.agroaqua.api.identity.infrastructure.persistence.UserJpaEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.nio.file.AccessDeniedException;

@Component
public class SecurityUtils {

    public Long getLoggedUserId() throws AccessDeniedException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("User not authenticated");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof UserJpaEntity userJpa) {
            return userJpa.getId();
        }
        throw new AccessDeniedException("User not authenticated");
    }
}
