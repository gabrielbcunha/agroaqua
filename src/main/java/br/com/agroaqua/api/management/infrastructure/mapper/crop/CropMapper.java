package br.com.agroaqua.api.management.infrastructure.mapper.crop;

import br.com.agroaqua.api.management.domain.crop.Crop;
import br.com.agroaqua.api.management.infrastructure.persistence.crop.CropJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface CropMapper {

    CropJpaEntity toJpaEntity(Crop crop);

    default Crop toDomain(CropJpaEntity cropJpaEntity) {
        if (cropJpaEntity == null) {
            return null;
        }
        return new Crop(
                cropJpaEntity.getId(),
                cropJpaEntity.getName(),
                cropJpaEntity.getDescription(),
                cropJpaEntity.getNormalHumidity(),
                cropJpaEntity.getStandardTimeToGrowInDays()
        );
    }
}