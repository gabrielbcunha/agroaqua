package br.com.agroaqua.api.management.application.dto.plot.create;

import java.math.BigDecimal;

public record PlotCreateResponse(
        Long id,
        String code,
        Long cropId,
        BigDecimal length,
        BigDecimal width,
        BigDecimal totalArea,
        BigDecimal usedArea,
        BigDecimal availableArea
) { }