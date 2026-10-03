package br.com.agroaqua.api.management.application.dto.crop.get;

import br.com.agroaqua.api.management.domain.crop.Crop;

import java.math.BigDecimal;

public record CropGetResponse(
        Long id,
        String name,
        String description,
        BigDecimal normalHumidity,
        int standardTimeToGrowInDays
) {
    public static CropGetResponse fromDomain(Crop crop){
        return new CropGetResponse(
                crop.getId(),
                crop.getName(),
                crop.getDescription(),
                crop.getNormalHumidity(),
                crop.getStandardTimeToGrowInDays()
        );
    }
}
