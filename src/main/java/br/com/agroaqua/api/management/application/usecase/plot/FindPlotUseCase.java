package br.com.agroaqua.api.management.application.usecase.plot;

import br.com.agroaqua.api.management.application.dto.plot.get.PlotGetResponse;
import br.com.agroaqua.api.management.domain.plot.Plot;
import br.com.agroaqua.api.management.domain.plot.PlotRepository;
import br.com.agroaqua.api.shared.domain.exception.ResourceNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
public class FindPlotUseCase {

    private final PlotRepository plotRepository;

    public FindPlotUseCase(PlotRepository plotRepository) {
        this.plotRepository = plotRepository;
    }

    public Slice<PlotGetResponse> findAll(Pageable pageable) {
        return plotRepository.findAll(pageable)
                .map(PlotGetResponse::fromDomain);
    }

    public PlotGetResponse findById(Long id){
        Plot plot = plotRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plot not found with id " + id));
        return PlotGetResponse.fromDomain(plot);
    }

    public PlotGetResponse findByCode(String code){
        Plot plot = plotRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Plot not found with code " + code));
        return PlotGetResponse.fromDomain(plot);
    }

    public Slice<PlotGetResponse> findByCropId(Long cropId, Pageable pageable) {
        return plotRepository.findByCropId(cropId, pageable)
                .map(PlotGetResponse::fromDomain);
    }
}