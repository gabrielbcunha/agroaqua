package br.com.agroaqua.api.management.domain.plot;

import java.util.List;
import java.util.Optional;

public interface PlotRepository {

    Plot save(Plot plot);

    Optional<Plot> findById(Long id);

    List<Plot> findAll();

}