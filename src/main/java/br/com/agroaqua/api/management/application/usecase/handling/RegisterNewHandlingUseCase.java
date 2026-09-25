package br.com.agroaqua.api.management.application.usecase.handling;

import br.com.agroaqua.api.management.application.dto.handling.create.HandlingCreateRequest;
import br.com.agroaqua.api.management.application.dto.handling.create.HandlingCreateResponse;
import br.com.agroaqua.api.management.domain.handling.Handling;
import br.com.agroaqua.api.management.domain.handling.HandlingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterNewHandlingUseCase {

    private final HandlingRepository handlingRepository;

    public RegisterNewHandlingUseCase(HandlingRepository handlingRepository) {
        this.handlingRepository = handlingRepository;
    }

    @Transactional
    public HandlingCreateResponse execute(HandlingCreateRequest request) {
        Handling newHandling = new Handling(
                request.employeeId(),
                request.plotId(),
                request.category(),
                request.description(),
                request.startDateTime(),
                request.endDateTime()
                );
        Handling savedHandling = handlingRepository.save(newHandling);
        return new HandlingCreateResponse(
                savedHandling.getId(),
                savedHandling.getEmployeeId(),
                savedHandling.getPlotId(),
                savedHandling.getCategory(),
                savedHandling.getDescription(),
                savedHandling.getStartDateTime(),
                savedHandling.getEndDateTime()
        );
    }


}