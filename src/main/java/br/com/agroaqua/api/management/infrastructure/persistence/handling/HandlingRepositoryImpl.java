package br.com.agroaqua.api.management.infrastructure.persistence.handling;

import br.com.agroaqua.api.management.domain.handling.Handling;
import br.com.agroaqua.api.management.domain.handling.HandlingRepository;
import br.com.agroaqua.api.management.infrastructure.mapper.handling.HandlingMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class HandlingRepositoryImpl implements HandlingRepository {

    private final HandlingSpringDataRepository handlingSpringDataRepository;
    private final HandlingMapper handlingMapper;

    public HandlingRepositoryImpl(HandlingSpringDataRepository handlingSpringDataRepository, HandlingMapper handlingMapper) {
        this.handlingSpringDataRepository = handlingSpringDataRepository;
        this.handlingMapper = handlingMapper;
    }

    @Override
    public Handling save(Handling handling) {
        HandlingJpaEntity handlingJpaEntity = handlingMapper.toJpaEntity(handling);
        HandlingJpaEntity saveEntity = handlingSpringDataRepository.save(handlingJpaEntity);
        return handlingMapper.toDomain(saveEntity);
    }

    @Override
    public Slice<Handling> findAll(Pageable pageable) {
        return handlingSpringDataRepository.findAll(pageable)
                .map(handlingMapper::toDomain);
    }

    @Override
    public Optional<Handling> findById(Long id) {
        return handlingSpringDataRepository.findById(id)
                .map(handlingMapper::toDomain);
    }

    @Override
    public Slice<Handling> findByEmployeeId(Long employeeId, Pageable pageable) {
        return handlingSpringDataRepository.findByEmployeeId(employeeId, pageable)
                .map(handlingMapper::toDomain);
    }

    @Override
    public Slice<Handling> findByPlotId(Long plotId, Pageable pageable) {
        return handlingSpringDataRepository.findByPlotId(plotId, pageable)
                .map(handlingMapper::toDomain);
    }
}