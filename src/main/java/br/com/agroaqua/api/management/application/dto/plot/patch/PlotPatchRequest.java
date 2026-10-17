package br.com.agroaqua.api.management.application.dto.plot.patch;

import java.math.BigDecimal;

public record PlotPatchRequest(
        String code,
        Long cropId,
        BigDecimal width,
        BigDecimal length,
        BigDecimal usedArea
) { }
