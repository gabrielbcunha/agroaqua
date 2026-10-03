package br.com.agroaqua.api.management.application.usecase.plot;

import br.com.agroaqua.api.management.application.dto.plot.get.PlotGetResponse;
import br.com.agroaqua.api.management.domain.plot.Plot;
import br.com.agroaqua.api.management.domain.plot.PlotRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindPlotUseCase {

    private final PlotRepository plotRepository;

    public FindPlotUseCase(PlotRepository plotRepository) {
        this.plotRepository = plotRepository;
    }

    public List<PlotGetResponse> findAll(){
        return plotRepository.findAll()
                .stream()
                .map(PlotGetResponse::fromDomain)
                .toList();
    }

    public PlotGetResponse findById(Long id){
        Plot plot = plotRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plot not found with id " + id));
        return PlotGetResponse.fromDomain(plot);
    }

    public PlotGetResponse findByCode(String code){
        Plot plot = plotRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("Plot not found with code " + code));
        return PlotGetResponse.fromDomain(plot);
    }

    public List<PlotGetResponse> findByCropId(Long cropId){
        return plotRepository.findByCropId(cropId)
                .stream()
                .map(PlotGetResponse::fromDomain)
                .toList();
    }

}