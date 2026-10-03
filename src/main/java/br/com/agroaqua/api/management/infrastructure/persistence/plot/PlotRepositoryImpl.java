package br.com.agroaqua.api.management.infrastructure.persistence.plot;

import br.com.agroaqua.api.management.domain.plot.Plot;
import br.com.agroaqua.api.management.domain.plot.PlotRepository;
import br.com.agroaqua.api.management.infrastructure.mapper.plot.PlotMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PlotRepositoryImpl implements PlotRepository {

    private final PlotSpringDataRepository plotSpringDataRepository;
    private final PlotMapper plotMapper;

    public PlotRepositoryImpl(PlotSpringDataRepository plotSpringDataRepository, PlotMapper plotMapper) {
        this.plotSpringDataRepository = plotSpringDataRepository;
        this.plotMapper = plotMapper;
    }


    @Override
    public Plot save(Plot plot) {
        PlotJpaEntity plotJpaEntity = plotMapper.toJpaEntity(plot);
        PlotJpaEntity savedEntity = plotSpringDataRepository.save(plotJpaEntity);
        return plotMapper.toDomain(savedEntity);
    }

    @Override
    public List<Plot> findAll() {
        return plotSpringDataRepository.findAll()
                .stream().map(plotMapper::toDomain).toList();
    }

    @Override
    public Optional<Plot> findById(Long id) {
        return plotSpringDataRepository.findById(id)
                .map(plotMapper::toDomain);
    }

    @Override
    public Optional<Plot> findByCode(String code) {
        return plotSpringDataRepository.findByCode(code)
                .map(plotMapper::toDomain);
    }

    @Override
    public List<Plot> findByCropId(Long cropId) {
        return plotSpringDataRepository.findByCropId(cropId)
                .stream()
                .map(plotMapper::toDomain)
                .toList();
    }
}