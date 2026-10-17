package br.com.agroaqua.api.management.application.dto.handling.patch;

import br.com.agroaqua.api.management.domain.handling.HandlingCategory;

import java.time.LocalDateTime;

public record HandlingPatchRequest(
       Long employeeId,
       Long plotId,
       HandlingCategory category,
       String description,
       LocalDateTime startDateTime,
       LocalDateTime endDateTime
) { }