package br.com.agroaqua.api.management.application.dto.crop.create;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CropCreateRequest(

        @NotBlank(message = "The name field must be filled in")
        String name,

        @NotBlank(message = "The description field must be filled in")
        @Size(max=200, message = "The description field must contain fewer than 200 characters.")
        String description,


        @NotNull(message = "The normal humidity field must be filled in")
        @DecimalMin(value = "0.00", inclusive = false)
        @DecimalMax(value = "100.00", inclusive = true)
        @Digits(integer = 3, fraction = 2)
        BigDecimal normalHumidity,

        @NotNull(message = "The standard time to grow in days field must be filled in")
        @Positive(message = "The standard time to grow in days field must be greater than 0")
        int standardTimeToGrowInDays
) { }
