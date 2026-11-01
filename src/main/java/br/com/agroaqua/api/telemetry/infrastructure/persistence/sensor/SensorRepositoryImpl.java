package br.com.agroaqua.api.telemetry.infrastructure.persistence.sensor;

import br.com.agroaqua.api.telemetry.domain.sensor.Sensor;
import br.com.agroaqua.api.telemetry.domain.sensor.SensorRepository;
import br.com.agroaqua.api.telemetry.infrastructure.mapper.sensor.SensorMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SensorRepositoryImpl implements SensorRepository {

    private final SensorSpringDataRepository sensorSpringDataRepository;
    private final SensorMapper sensorMapper;

    public SensorRepositoryImpl(SensorSpringDataRepository sensorSpringDataRepository, SensorMapper sensorMapper) {
        this.sensorSpringDataRepository = sensorSpringDataRepository;
        this.sensorMapper = sensorMapper;
    }

    @Override
    public Sensor save(Sensor sensor) {
        SensorJpaEntity sensorJpaEntity = sensorMapper.toJpaEntity(sensor);
        SensorJpaEntity savedEntity = sensorSpringDataRepository.save(sensorJpaEntity);
        return sensorMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Sensor> findById(Long id) {
        return sensorSpringDataRepository.findById(id)
                .map(sensorMapper::toDomain);
    }

    @Override
    public Slice<Sensor> findAll(Pageable pageable) {
        return sensorSpringDataRepository.findAll(pageable)
                .map(sensorMapper::toDomain);
    }

    @Override
    public Optional<Sensor> findByCode(String sensorCode) {
        return sensorSpringDataRepository.findByCode(sensorCode)
                .map(sensorMapper::toDomain);
    }
}
