package br.com.agroaqua.api.management.application.usecase.handling;

import br.com.agroaqua.api.management.application.dto.handling.get.HandlingGetResponse;
import br.com.agroaqua.api.management.domain.handling.Handling;
import br.com.agroaqua.api.management.domain.handling.HandlingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindHandlingUseCase {

    private final HandlingRepository handlingRepository;

    public FindHandlingUseCase(HandlingRepository handlingRepository) {
        this.handlingRepository = handlingRepository;
    }

    public List<HandlingGetResponse> findAll(){
        return handlingRepository.findAll()
                .stream()
                .map(HandlingGetResponse::fromDomain)
                .toList();
    }

    public HandlingGetResponse findById(Long id){
        Handling handling = handlingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Handling not found with id: " + id));
        return HandlingGetResponse.fromDomain(handling);
    }

    public List<HandlingGetResponse> findByEmployeeId(Long employeeId){
        return handlingRepository.findByEmployeeId(employeeId)
                .stream()
                .map(HandlingGetResponse::fromDomain)
                .toList();
    }

    public List<HandlingGetResponse> findByPlotId(Long plotId){
        return handlingRepository.findByPlotId(plotId)
                .stream()
                .map(HandlingGetResponse::fromDomain)
                .toList();
    }

}