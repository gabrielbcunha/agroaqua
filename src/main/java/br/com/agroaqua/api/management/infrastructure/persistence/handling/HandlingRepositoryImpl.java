package br.com.agroaqua.api.management.infrastructure.persistence.handling;

import br.com.agroaqua.api.management.domain.handling.Handling;
import br.com.agroaqua.api.management.domain.handling.HandlingRepository;
import br.com.agroaqua.api.management.infrastructure.mapper.handling.HandlingMapper;
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

    //mudar
    @Override
    public Optional<Handling> findById(Long id) {
        return Optional.empty();
    }

    //mudar
    @Override
    public List<Handling> findAll() {
        return List.of();
    }
}