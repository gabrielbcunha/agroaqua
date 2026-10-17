package br.com.agroaqua.api.management.application.usecase.plot;

import br.com.agroaqua.api.management.application.dto.plot.get.PlotGetResponse;
import br.com.agroaqua.api.management.application.dto.plot.patch.PlotPatchRequest;
import br.com.agroaqua.api.management.domain.plot.Plot;
import br.com.agroaqua.api.management.domain.plot.PlotRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatchPlotUseCase {

    private final PlotRepository plotRepository;

    public PatchPlotUseCase(PlotRepository plotRepository) {
        this.plotRepository = plotRepository;
    }

    @Transactional
    public PlotGetResponse execute(Long id, PlotPatchRequest request) {

        if (id == null) {
            throw new IllegalArgumentException("Id is required");
        }

        Plot plot = plotRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Plot not found with id " + id));

        if (request.code() != null && !request.code().trim().isEmpty()) {
            plot.changeCode(request.code());
        }

        if (request.cropId() != null) {
            plot.changeCropId(request.cropId());
        }

        plot.changeWidthLengthUsedArea(request.width(), request.length(), request.usedArea());

        Plot patchedPlot = plotRepository.save(plot);
        return  PlotGetResponse.fromDomain(patchedPlot);
    }
}