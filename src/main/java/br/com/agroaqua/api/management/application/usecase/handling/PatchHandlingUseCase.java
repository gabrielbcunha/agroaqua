package br.com.agroaqua.api.management.application.usecase.handling;

import br.com.agroaqua.api.management.application.dto.handling.get.HandlingGetResponse;
import br.com.agroaqua.api.management.application.dto.handling.patch.HandlingPatchRequest;
import br.com.agroaqua.api.management.domain.handling.Handling;
import br.com.agroaqua.api.management.domain.handling.HandlingRepository;
import org.springframework.stereotype.Service;

@Service
public class PatchHandlingUseCase {

    private final HandlingRepository handlingRepository;

    public PatchHandlingUseCase(HandlingRepository handlingRepository) {
        this.handlingRepository = handlingRepository;
    }

    public HandlingGetResponse execute(Long id, HandlingPatchRequest request) {

        if (id == null) {
            throw new IllegalArgumentException("Id is required");
        }

        Handling handling = handlingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Handling not found with id " + id));

        if (request.employeeId() != null) {
            handling.changeEmployeeId(request.employeeId());
        }

        if (request.plotId() != null) {
            handling.changePlotId(request.plotId());
        }

        if (request.category() != null) {
            handling.changeCategory(request.category());
        }

        if (request.description() != null && !request.description().trim().isEmpty()) {
            handling.changeDescription(request.description());
        }

        if (request.startDateTime() != null) {
            handling.changeStartDateTime(request.startDateTime());
        }

        if (request.endDateTime() != null) {
            handling.changeEndDateTime(request.endDateTime());
        }

        Handling patchedHandling = handlingRepository.save(handling);
        return HandlingGetResponse.fromDomain(patchedHandling);
    }
}