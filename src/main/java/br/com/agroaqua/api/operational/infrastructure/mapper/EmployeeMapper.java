package br.com.agroaqua.api.operational.infrastructure.mapper;

import br.com.agroaqua.api.operational.domain.employee.Employee;
import br.com.agroaqua.api.operational.infrastructure.persistence.EmployeeJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface EmployeeMapper {

    EmployeeJpaEntity toJpaEntity(Employee employee);

    default Employee toDomain(EmployeeJpaEntity employeeJpaEntity) {
        if (employeeJpaEntity == null) {
            return null;
        }
        return new Employee(
                employeeJpaEntity.getId(),
                employeeJpaEntity.getUserId(),
                employeeJpaEntity.getName()
        );
    }

}