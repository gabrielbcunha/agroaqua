package br.com.agroaqua.api.management.infrastructure.mapper.plot;

import br.com.agroaqua.api.management.domain.plot.Plot;
import br.com.agroaqua.api.management.infrastructure.persistence.plot.PlotJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface PlotMapper {

    PlotJpaEntity toJpaEntity(Plot plot);

    default Plot toDomain(PlotJpaEntity plotJpaEntity) {
        if (plotJpaEntity == null) {
            return null;
        }
        return new Plot(
                plotJpaEntity.getId(),
                plotJpaEntity.getCode(),
                plotJpaEntity.getCropId(),
                plotJpaEntity.getLength(),
                plotJpaEntity.getWidth(),
                plotJpaEntity.getUsedArea()
        );
    }
}
