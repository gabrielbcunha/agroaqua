package br.com.agroaqua.api.management.application.usecase.handling;

import br.com.agroaqua.api.management.application.dto.handling.get.HandlingGetResponse;
import br.com.agroaqua.api.management.domain.handling.Handling;
import br.com.agroaqua.api.management.domain.handling.HandlingRepository;
import br.com.agroaqua.api.shared.domain.exception.ResourceNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
public class FindHandlingUseCase {

    private final HandlingRepository handlingRepository;

    public FindHandlingUseCase(HandlingRepository handlingRepository) {
        this.handlingRepository = handlingRepository;
    }

    public Slice<HandlingGetResponse> findAll(Pageable pageable) {
        return handlingRepository.findAll(pageable)
                .map(HandlingGetResponse::fromDomain);
    }

    public HandlingGetResponse findById(Long id){
        Handling handling = handlingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Handling not found with id: " + id));
        return HandlingGetResponse.fromDomain(handling);
    }

    public Slice<HandlingGetResponse> findByEmployeeId(Long employeeId, Pageable pageable) {
        return handlingRepository.findByEmployeeId(employeeId, pageable)
                .map(HandlingGetResponse::fromDomain);
    }

    public Slice<HandlingGetResponse> findByPlotId(Long plotId, Pageable pageable){
        return handlingRepository.findByPlotId(plotId, pageable)
                .map(HandlingGetResponse::fromDomain);
    }

}