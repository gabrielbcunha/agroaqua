package br.com.agroaqua.api.management.application.dto.crop.patch;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CropPatchRequest(

        String name,

        @Size(max=200, message = "The description field must contain fewer than 200 characters.")
        String description,

        @DecimalMin(value = "0.00", inclusive = false)
        @DecimalMax(value = "100.00", inclusive = true)
        @Digits(integer = 3, fraction = 2)
        BigDecimal normalHumidity,

        @Positive(message = "The standard time to grow in days field must be greater than 0")
        Integer standardTimeToGrowInDays
) { }