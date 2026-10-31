package br.com.agroaqua.api.management.application.dto.handling.patch;

import br.com.agroaqua.api.management.domain.handling.HandlingCategory;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record HandlingPatchRequest(

       Long employeeId,

       Long plotId,
       HandlingCategory category,

       @Size(max=200, message = "The description field must contain fewer than 200 characters.")
       String description,

       LocalDateTime startDateTime,

       LocalDateTime endDateTime
) { }