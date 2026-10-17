package br.com.agroaqua.api.operational.application.dto.anonymize;

import br.com.agroaqua.api.operational.domain.employee.Employee;

public record EmployeeAnonymizeResponse (
        Long id,
        Long userId,
        String name
) {
   public static EmployeeAnonymizeResponse fromDomain(Employee employee) {
       return new EmployeeAnonymizeResponse(
               employee.getId(),
               employee.getUserId(),
               employee.getName()
       );
   }
}