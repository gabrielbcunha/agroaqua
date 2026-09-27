package br.com.agroaqua.api.telemetry.infrastructure.mapper.sensor;

import br.com.agroaqua.api.telemetry.domain.sensor.Sensor;
import br.com.agroaqua.api.telemetry.infrastructure.persistence.sensor.SensorJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface SensorMapper {

    SensorJpaEntity toJpaEntity(Sensor sensor);

    default Sensor toDomain(SensorJpaEntity sensorJpaEntity) {
        if (sensorJpaEntity == null) {
            return null;
        }
        return new Sensor(
                sensorJpaEntity.getId(),
                sensorJpaEntity.getCode(),
                sensorJpaEntity.getPlotId(),
                sensorJpaEntity.isActive()
        );
    }
}