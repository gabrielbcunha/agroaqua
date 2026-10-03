package br.com.agroaqua.api.management.application.dto.plot.get;

import br.com.agroaqua.api.management.domain.plot.Plot;

import java.math.BigDecimal;

public record PlotGetResponse(
        Long id,
        String code,
        Long cropId,
        BigDecimal length,
        BigDecimal width,
        BigDecimal totalArea,
        BigDecimal usedArea,
        BigDecimal availableArea
) {
    public static PlotGetResponse fromDomain(Plot plot) {
        return new PlotGetResponse(
                plot.getId(),
                plot.getCode(),
                plot.getCropId(),
                plot.getLength(),
                plot.getWidth(),
                plot.getTotalArea(),
                plot.getUsedArea(),
                plot.getAvailableArea()
        );
    }
}
