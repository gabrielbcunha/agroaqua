package br.com.agroaqua.api.operational.domain.employee;

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
            throw new IllegalArgumentException("Id cannot be null");
        }
        this.id = id;
    }

    private void validateInput(Long userId,String name) {
        if(userId == null) {
        throw new IllegalArgumentException("User Id cannot be null");
        } else if (name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank");
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
}