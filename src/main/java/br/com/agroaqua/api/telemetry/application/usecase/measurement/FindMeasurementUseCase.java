package br.com.agroaqua.api.telemetry.application.usecase.measurement;

import br.com.agroaqua.api.telemetry.application.dto.measurement.get.MeasurementGetResponse;
import br.com.agroaqua.api.telemetry.domain.measurement.Measurement;
import br.com.agroaqua.api.telemetry.domain.measurement.MeasurementRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindMeasurementUseCase {

    private final MeasurementRepository measurementRepository;

    public FindMeasurementUseCase(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public MeasurementGetResponse findById(Long id){
        Measurement measurement = measurementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Measurement not found with id " + id));
        return MeasurementGetResponse.fromDomain(measurement);
    }

    public List<MeasurementGetResponse> findAll(){
        return measurementRepository.findAll()
                .stream()
                .map(MeasurementGetResponse::fromDomain)
                .toList();
    }

}
