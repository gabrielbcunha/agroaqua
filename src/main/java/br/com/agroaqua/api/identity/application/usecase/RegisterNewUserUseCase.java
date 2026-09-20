package br.com.agroaqua.api.identity.application.usecase;

import br.com.agroaqua.api.identity.application.dto.create.UserCreateRequest;
import br.com.agroaqua.api.identity.domain.user.User;
import br.com.agroaqua.api.identity.domain.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterNewUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterNewUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Long execute(UserCreateRequest request) {

        String encodedPassword = passwordEncoder.encode(request.password());

        User newUser = new User(request.email(), encodedPassword,request.role());

        User savedUser = userRepository.save(newUser);
        return savedUser.getId();
    }
}