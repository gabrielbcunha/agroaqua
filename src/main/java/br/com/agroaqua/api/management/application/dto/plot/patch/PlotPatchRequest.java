package br.com.agroaqua.api.management.application.dto.plot.patch;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record PlotPatchRequest(

        String code,

        Long cropId,

        @Positive(message = "The length field must be greater than 0")
        BigDecimal width,

        @Positive(message = "The width field must be greater than 0")
        BigDecimal length,

        @PositiveOrZero(message = "The used area field must be zero or positive")
        BigDecimal usedArea
) { }
