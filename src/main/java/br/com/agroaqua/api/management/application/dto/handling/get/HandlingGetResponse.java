package br.com.agroaqua.api.management.application.dto.handling.get;

import br.com.agroaqua.api.management.domain.handling.Handling;
import br.com.agroaqua.api.management.domain.handling.HandlingCategory;

import java.time.LocalDateTime;

public record HandlingGetResponse(
        Long id,
        Long employeeId,
        Long plotId,
        HandlingCategory category,
        String description,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime
) {
    public static HandlingGetResponse fromDomain(Handling handling) {
        return new HandlingGetResponse(
          handling.getId(),
          handling.getEmployeeId(),
          handling.getPlotId(),
          handling.getCategory(),
          handling.getDescription(),
          handling.getStartDateTime(),
          handling.getEndDateTime()
        );
    }
}
