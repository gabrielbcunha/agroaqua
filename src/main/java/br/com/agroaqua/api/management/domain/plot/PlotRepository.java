package br.com.agroaqua.api.management.domain.plot;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface PlotRepository {

    Plot save(Plot plot);

    Optional<Plot> findById(Long id);

    Slice<Plot> findAll(Pageable pageable);

    Optional<Plot> findByCode(String code);

    Slice<Plot> findByCropId(Long cropId, Pageable pageable);
}