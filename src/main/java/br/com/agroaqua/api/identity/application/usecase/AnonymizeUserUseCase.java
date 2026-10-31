package br.com.agroaqua.api.identity.application.usecase;

import br.com.agroaqua.api.identity.domain.user.User;
import br.com.agroaqua.api.identity.domain.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnonymizeUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AnonymizeUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void execute(Long id){
        if (id == null){
            throw new IllegalArgumentException("Id cannot be null");
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id " + id));

        String anonymizedEmail = "anonymizedWithId" + id + "@agroaqua.com";
        user.anonymizeEmail(anonymizedEmail);

        String lockedPassword = java.util.UUID.randomUUID().toString();
        user.anonymizePassword(passwordEncoder.encode(lockedPassword));

        userRepository.save(user);
    }
}