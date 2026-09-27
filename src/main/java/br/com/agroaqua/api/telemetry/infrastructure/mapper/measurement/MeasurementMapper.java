package br.com.agroaqua.api.telemetry.infrastructure.mapper.measurement;

import br.com.agroaqua.api.telemetry.domain.measurement.Measurement;
import br.com.agroaqua.api.telemetry.infrastructure.persistence.measurement.MeasurementJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface MeasurementMapper {

    MeasurementJpaEntity toJpaEntity(Measurement measurement);

    default Measurement toDomain(MeasurementJpaEntity measurementJpaEntity) {
        if (measurementJpaEntity == null) {
            return null;
        }
        return new Measurement(
                measurementJpaEntity.getId(),
                measurementJpaEntity.getSensorId(),
                measurementJpaEntity.getHumidity(),
                measurementJpaEntity.getMeasuredAt()
        );
    }
}