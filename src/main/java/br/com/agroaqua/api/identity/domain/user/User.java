package br.com.agroaqua.api.identity.domain.user;

import br.com.agroaqua.api.shared.domain.Role;

public class User {
    private Long id;
    private String email;
    private String password;
    private Role role;

    public User(String email, String password, Role role) {
        validateInput(email, password, role);
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(Long id, String email, String password, Role role) {
        this(email, password, role);
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        this.id = id;
    }

    private void validateInput(String email, String password, Role role) {
        if (email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be blank");
        } else if (password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be blank");
        } else if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
