package br.com.agroaqua.api.operational.domain.employee;

import br.com.agroaqua.api.shared.domain.exception.DomainValidationException;

public class Employee {
    private Long id;
    private String name;
    private Long userId;

    public Employee(Long userId, String name) {
        validateInput(userId, name);
        this.userId = userId;
        this.name = name;
    }

    public Employee(Long id, Long userId, String name) {
        this(userId, name);
        if (id == null) {
            throw new DomainValidationException("Id cannot be null");
        }
        this.id = id;
    }

    private void validateInput(Long userId,String name) {
        if(userId == null) {
        throw new DomainValidationException("User Id cannot be null");
        } else if (name == null || name.isBlank()) {
            throw new DomainValidationException("Name cannot be null or blank");
        }
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void changeName(String newName) {
        if (newName == null || newName.isBlank()) {
            throw new DomainValidationException("Name cannot be null or blank");
        }
        this.name = newName;
    }

    public void anonymizeEmployee(String anonymizedName) {
        if (anonymizedName == null || anonymizedName.isBlank()) {
            throw new DomainValidationException("Anonymized name cannot be null or blank");
        }
        this.name = anonymizedName;
    }
}