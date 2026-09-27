package br.com.agroaqua.api.telemetry.infrastructure.persistence.measurement;

import br.com.agroaqua.api.telemetry.domain.measurement.Measurement;
import br.com.agroaqua.api.telemetry.domain.measurement.MeasurementRepository;
import br.com.agroaqua.api.telemetry.infrastructure.mapper.measurement.MeasurementMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MeasurementRepositoryImpl implements MeasurementRepository {

    private final MeasurementSpringDataRepository measurementSpringDataRepository;
    private final MeasurementMapper measurementMapper;

    public MeasurementRepositoryImpl(MeasurementSpringDataRepository measurementSpringDataRepository, MeasurementMapper measurementMapper) {
        this.measurementSpringDataRepository = measurementSpringDataRepository;
        this.measurementMapper = measurementMapper;
    }

    @Override
    public Measurement save(Measurement measurement) {
        MeasurementJpaEntity measurementJpaEntity = measurementMapper.toJpaEntity(measurement);
        MeasurementJpaEntity savedMeasurement = measurementSpringDataRepository.save(measurementJpaEntity);
        return measurementMapper.toDomain(savedMeasurement);
    }

    //Mudar
    @Override
    public Optional<Measurement> findById(Long id) {
        return Optional.empty();
    }

    //Mudar
    @Override
    public List<Measurement> findAll() {
        return List.of();
    }
}