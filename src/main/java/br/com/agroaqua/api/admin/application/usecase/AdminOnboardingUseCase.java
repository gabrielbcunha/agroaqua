package br.com.agroaqua.api.admin.application.usecase;

import br.com.agroaqua.api.admin.application.dto.AdminOnboardingRequest;
import br.com.agroaqua.api.admin.application.dto.AdminOnboardingResponse;
import br.com.agroaqua.api.identity.application.dto.create.UserCreateRequest;
import br.com.agroaqua.api.identity.application.usecase.RegisterNewUserUseCase;
import br.com.agroaqua.api.operational.application.dto.EmployeeCreateRequest;
import br.com.agroaqua.api.operational.application.usecase.RegisterNewEmployeeUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminOnboardingUseCase {

    private final RegisterNewUserUseCase registerUser;
    private final RegisterNewEmployeeUseCase registerEmployee;

    public AdminOnboardingUseCase(RegisterNewUserUseCase registerUser, RegisterNewEmployeeUseCase registerEmployee) {
        this.registerUser = registerUser;
        this.registerEmployee = registerEmployee;
    }

    public AdminOnboardingResponse execute(AdminOnboardingRequest request) {
        UserCreateRequest userDTO = new UserCreateRequest(request.email(), request.password(),request.role());
        Long userId = registerUser.execute(userDTO);

        EmployeeCreateRequest employeeDTO = new EmployeeCreateRequest(userId, request.name());
        registerEmployee.execute(employeeDTO);

        return new AdminOnboardingResponse(request.name(), request.email(), userId, request.role());
    }
}