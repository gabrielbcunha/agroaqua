package br.com.agroaqua.api.management.application.usecase.plot;

import br.com.agroaqua.api.management.application.dto.plot.create.PlotCreateRequest;
import br.com.agroaqua.api.management.application.dto.plot.create.PlotCreateResponse;
import br.com.agroaqua.api.management.domain.plot.Plot;
import br.com.agroaqua.api.management.domain.plot.PlotRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterNewPlotUseCase {

    private final PlotRepository plotRepository;

    public RegisterNewPlotUseCase(PlotRepository plotRepository) {
        this.plotRepository = plotRepository;
    }

    @Transactional
    public PlotCreateResponse execute(PlotCreateRequest request) {
        Plot newPlot = new Plot(request.code(), request.cropId(), request.length(), request.width(), request.usedArea());
        Plot savedPlot = plotRepository.save(newPlot);
        return new PlotCreateResponse(savedPlot.getId(), savedPlot.getCode(), savedPlot.getCropId(), savedPlot.getLength(), savedPlot.getWidth(), savedPlot.getTotalArea(), savedPlot.getUsedArea(), savedPlot.getAvailableArea());
    }

}