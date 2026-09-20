package br.com.agroaqua.api.identity.infrastructure.seeder;

import br.com.agroaqua.api.identity.domain.user.User;
import br.com.agroaqua.api.identity.domain.user.UserRepository;
import br.com.agroaqua.api.shared.domain.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminUserSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminUserSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${first.admin.email}")
    String email;

    @Value("${first.admin.password}")
    String password;

    @Override
    public void run(String... args) throws Exception {

        Optional<User> existingUser = userRepository.findByEmail(email);

        if (existingUser.isEmpty()) {
            String encodedPassword = passwordEncoder.encode(password);
            User adminUser = new User(email, encodedPassword, Role.ADMIN);
            userRepository.save(adminUser);
        }
    }
}
