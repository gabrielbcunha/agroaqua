package br.com.agroaqua.api.identity.application.usecase;

import br.com.agroaqua.api.identity.application.dto.get.UserGetResponse;
import br.com.agroaqua.api.identity.application.dto.patch.UserPatchRequest;
import br.com.agroaqua.api.identity.domain.user.User;
import br.com.agroaqua.api.identity.domain.user.UserRepository;
import br.com.agroaqua.api.shared.domain.exception.DomainAuthorizationException;
import br.com.agroaqua.api.shared.domain.exception.DomainValidationException;
import br.com.agroaqua.api.shared.domain.exception.ResourceNotFoundException;
import br.com.agroaqua.api.shared.infrastructure.security.SecurityUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PatchUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecurityUtils securityUtils;
    private final AuthenticationManager authenticationManager;

    public PatchUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder, SecurityUtils securityUtils, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.securityUtils = securityUtils;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    public UserGetResponse execute(Long id, UserPatchRequest request) {

        if (id == null) {
            throw new DomainValidationException("id is required");
        }

        if (request.currentPassword() == null || request.currentPassword().isEmpty()) {
            throw new DomainValidationException("Password is required");
        }

        Long loggedUserId = securityUtils.getLoggedUserId();

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        if (!user.getId().equals(loggedUserId)) {
            throw new DomainAuthorizationException("Access denied, you are not authorized to perform this action");
        }

        try {
            UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(user.getEmail(), request.currentPassword());
            Authentication authentication = authenticationManager.authenticate(login);

            if(request.email() != null && !request.email().isEmpty()) {
                user.changeEmail(request.email());
            }

            if(request.password() != null && !request.password().isEmpty()) {
                String encodedPassword = passwordEncoder.encode(request.password());
                user.changePassword(encodedPassword);
            }

            User patchedUser = userRepository.save(user);
            return UserGetResponse.fromDomain(patchedUser);
        } catch (BadCredentialsException e) {
            throw new DomainValidationException("Password is incorrect");
        }
    }
}
