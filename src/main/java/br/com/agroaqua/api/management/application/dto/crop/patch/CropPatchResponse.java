package br.com.agroaqua.api.management.application.dto.crop.patch;

import java.math.BigDecimal;

public record CropPatchResponse(
        Long id,
        String name,
        String description,
        BigDecimal normalHumidity,
        int standardTimeToGrowInDays
) { }