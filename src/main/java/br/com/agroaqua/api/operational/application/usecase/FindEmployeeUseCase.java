package br.com.agroaqua.api.operational.application.usecase;

import br.com.agroaqua.api.operational.application.dto.get.EmployeeGetResponse;
import br.com.agroaqua.api.operational.domain.employee.Employee;
import br.com.agroaqua.api.operational.domain.employee.EmployeeRepository;
import br.com.agroaqua.api.shared.domain.exception.ResourceNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;


@Service
public class FindEmployeeUseCase {

    private final EmployeeRepository employeeRepository;

    public FindEmployeeUseCase(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeGetResponse findById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        return EmployeeGetResponse.fromDomain(employee);
    }

    public Slice<EmployeeGetResponse> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable)
                .map(EmployeeGetResponse::fromDomain);
    }
}