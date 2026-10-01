package br.com.agroaqua.api.telemetry.infrastructure.persistence.measurement;

import br.com.agroaqua.api.telemetry.domain.measurement.Measurement;
import br.com.agroaqua.api.telemetry.domain.measurement.MeasurementRepository;
import br.com.agroaqua.api.telemetry.infrastructure.mapper.measurement.MeasurementMapper;
import br.com.agroaqua.api.telemetry.infrastructure.persistence.sensor.SensorJpaEntity;
import br.com.agroaqua.api.telemetry.infrastructure.persistence.sensor.SensorSpringDataRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MeasurementRepositoryImpl implements MeasurementRepository {

    private final MeasurementSpringDataRepository measurementSpringDataRepository;
    private final SensorSpringDataRepository sensorSpringDataRepository;
    private final MeasurementMapper measurementMapper;

    public MeasurementRepositoryImpl(MeasurementSpringDataRepository measurementSpringDataRepository, SensorSpringDataRepository sensorSpringDataRepository, MeasurementMapper measurementMapper) {
        this.measurementSpringDataRepository = measurementSpringDataRepository;
        this.sensorSpringDataRepository = sensorSpringDataRepository;
        this.measurementMapper = measurementMapper;
    }

    @Override
    public Measurement save(Measurement measurement) {
        MeasurementJpaEntity measurementJpaEntity = measurementMapper.toJpaEntity(measurement);
        MeasurementJpaEntity savedMeasurement = measurementSpringDataRepository.save(measurementJpaEntity);
        return measurementMapper.toDomain(savedMeasurement);
    }

    @Override
    public Optional<Measurement> findById(Long id) {
        return measurementSpringDataRepository.findById(id)
                .map(measurementMapper::toDomain);
    }

    @Override
    public List<Measurement> findAll() {
        return measurementSpringDataRepository.findAll()
                .stream().map(measurementMapper::toDomain).toList();
    }

    @Override
    public List<Measurement> findAllBySensorId(Long sensorId) {
        return measurementSpringDataRepository.findBySensorId(sensorId)
                .stream().map(measurementMapper::toDomain).toList();
    }

    @Override
    public List<Measurement> findBySensorCode(String code) {
        SensorJpaEntity sensorByCode = sensorSpringDataRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("Sensor not found with code: " + code));
        return measurementSpringDataRepository.findBySensorId(sensorByCode.getId())
                .stream().map(measurementMapper::toDomain).toList();
    }
}