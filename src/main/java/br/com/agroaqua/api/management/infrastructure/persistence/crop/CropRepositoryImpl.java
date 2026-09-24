package br.com.agroaqua.api.management.infrastructure.persistence.crop;

import br.com.agroaqua.api.management.domain.crop.Crop;
import br.com.agroaqua.api.management.domain.crop.CropRepository;
import br.com.agroaqua.api.management.infrastructure.mapper.crop.CropMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CropRepositoryImpl implements CropRepository {

    private final CropSpringDataRepository cropSpringDataRepository;
    private final CropMapper cropMapper;

    public CropRepositoryImpl(CropSpringDataRepository cropSpringDataRepository, CropMapper cropMapper) {
        this.cropSpringDataRepository = cropSpringDataRepository;
        this.cropMapper = cropMapper;
    }


    @Override
    public Crop save(Crop crop) {
        CropJpaEntity cropJpaEntity = cropMapper.toJpaEntity(crop);
        CropJpaEntity savedEntity = cropSpringDataRepository.save(cropJpaEntity);
        return cropMapper.toDomain(savedEntity);
    }

    //Mudar
    @Override
    public Optional<Crop> findById(Long id) {
        return Optional.empty();
    }

    //Mudar
    @Override
    public List<Crop> findAll() {
        return List.of();
    }
}
