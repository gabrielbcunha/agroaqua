package br.com.agroaqua.api.management.infrastructure.mapper.handling;

import br.com.agroaqua.api.management.domain.handling.Handling;
import br.com.agroaqua.api.management.infrastructure.persistence.handling.HandlingJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface HandlingMapper {

    HandlingJpaEntity toJpaEntity(Handling handling);

    default Handling toDomain(HandlingJpaEntity handlingJpaEntity) {
        if (handlingJpaEntity == null) {
            return null;
        }
        return new Handling(
                handlingJpaEntity.getId(),
                handlingJpaEntity.getEmployeeId(),
                handlingJpaEntity.getPlotId(),
                handlingJpaEntity.getCategory(),
                handlingJpaEntity.getDescription(),
                handlingJpaEntity.getStartDateTime(),
                handlingJpaEntity.getEndDateTime()
        );
    }
}