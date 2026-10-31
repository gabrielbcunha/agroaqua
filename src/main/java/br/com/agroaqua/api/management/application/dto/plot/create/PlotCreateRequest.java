package br.com.agroaqua.api.management.application.dto.plot.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record PlotCreateRequest(

        @NotBlank(message = "The code field must be filled in")
        String code,

        @NotNull(message = "The crop ID field must be filled in")
        Long cropId,

        @NotNull(message = "The length field must be filled in")
        @Positive(message = "The length field must be greater than 0")
        BigDecimal length,

        @NotNull(message = "The width field must be filled in")
        @Positive(message = "The width field must be greater than 0")
        BigDecimal width,

        @NotNull(message = "The used area field must be filled in")
        @PositiveOrZero(message = "The used area field must be zero or positive")
        BigDecimal usedArea
) { }