package br.com.agroaqua.api.operational.application.dto.get;

import br.com.agroaqua.api.operational.domain.employee.Employee;

public record EmployeeGetResponse (
        Long id,
        String name,
        Long userId
) {
    public static EmployeeGetResponse fromDomain(Employee employee) {
        return new EmployeeGetResponse(
                employee.getId(),
                employee.getName(),
                employee.getUserId()
        );
    }
}