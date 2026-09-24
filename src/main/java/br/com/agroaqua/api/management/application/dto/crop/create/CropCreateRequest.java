package br.com.agroaqua.api.management.application.dto.crop.create;

import java.math.BigDecimal;

public record CropCreateRequest(String name, String description, BigDecimal normalHumidity, int standardTimeToGrowInDays) {
}
