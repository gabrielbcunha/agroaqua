package br.com.agroaqua.api.operational.application.usecase;

import br.com.agroaqua.api.operational.application.dto.get.EmployeeGetResponse;
import br.com.agroaqua.api.operational.domain.employee.Employee;
import br.com.agroaqua.api.operational.domain.employee.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindEmployeeUseCase {

    private final EmployeeRepository employeeRepository;

    public FindEmployeeUseCase(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeGetResponse findById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));
        return EmployeeGetResponse.fromDomain(employee);
    }

    public List<EmployeeGetResponse> findAll(){
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeGetResponse::fromDomain)
                .toList();
    }
}