package br.com.agroaqua.api.shared.infrastructure.security;

import br.com.agroaqua.api.identity.infrastructure.persistence.UserJpaEntity;
import br.com.agroaqua.api.shared.domain.exception.DomainAuthorizationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    public Long getLoggedUserId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new DomainAuthorizationException("User not authenticated");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof UserJpaEntity userJpa) {
            return userJpa.getId();
        }
        throw new DomainAuthorizationException("User not authenticated");
    }

    public String getLoggedUsername() throws DomainAuthorizationException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new DomainAuthorizationException("User not authenticated");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof UserJpaEntity userJpa) {
            return userJpa.getUsername();
        }
        throw new DomainAuthorizationException("User not authenticated");
    }
}
