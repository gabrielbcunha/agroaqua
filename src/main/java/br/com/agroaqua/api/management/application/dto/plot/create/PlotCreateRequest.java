package br.com.agroaqua.api.management.application.dto.plot.create;

import java.math.BigDecimal;

public record PlotCreateRequest(
        String code,
        Long cropId,
        BigDecimal length,
        BigDecimal width,
        BigDecimal usedArea
) { }