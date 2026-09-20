package br.com.agroaqua.api.identity.application.usecase;

import br.com.agroaqua.api.identity.application.dto.findbyemail.UserFindByEmailRequest;
import br.com.agroaqua.api.identity.domain.user.User;
import br.com.agroaqua.api.identity.domain.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class FindUserByEmailUseCase {

    private final UserRepository userRepository;

    public FindUserByEmailUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> execute(UserFindByEmailRequest request) {
        return Optional.of(userRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalArgumentException("No User found with email: " + request.email())));
    }

}
