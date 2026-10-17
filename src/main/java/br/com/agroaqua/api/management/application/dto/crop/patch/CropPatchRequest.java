package br.com.agroaqua.api.management.application.dto.crop.patch;

import java.math.BigDecimal;

public record CropPatchRequest(
        String name,
        String description,
        BigDecimal normalHumidity,
        Integer standardTimeToGrowInDays
) { }