package br.com.agroaqua.api.management.domain.handling;

import br.com.agroaqua.api.shared.domain.exception.DomainValidationException;

import java.time.LocalDateTime;

public class Handling {
    private Long id;
    private Long employeeId;
    private Long plotId;
    private HandlingCategory category;
    private String description;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public Handling(Long employeeId, Long plotId, HandlingCategory category, String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        validateInput(employeeId, plotId, category, description, startDateTime, endDateTime);
        this.employeeId = employeeId;
        this.plotId = plotId;
        this.category = category;
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public Handling(Long id, Long employeeId, Long plotId, HandlingCategory category, String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this(employeeId, plotId, category, description, startDateTime, endDateTime);
        if(id == null) {
            throw new DomainValidationException("ID cannot be null");
        }
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public Long getPlotId() {
        return plotId;
    }

    public HandlingCategory getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void changeEmployeeId(Long employeeId) {
        if(employeeId == null) {
            throw new DomainValidationException("employeeId cannot be null");
        }
        this.employeeId = employeeId;
    }

    public void changePlotId(Long newPlotId) {
        if (newPlotId == null) {
            throw new DomainValidationException("New Plot ID cannot be null");
        }
        this.plotId = newPlotId;
    }

    public void changeCategory(HandlingCategory newCategory) {
        if (newCategory == null) {
            throw new DomainValidationException("New Category cannot be null");
        }
        this.category = newCategory;
    }

    public void changeDescription(String newDescription) {
        if (newDescription == null || newDescription.isBlank()) {
            throw new DomainValidationException("New Description cannot be null or blank");
        }
        this.description = newDescription;
    }

    public void changePeriod(LocalDateTime newStartDateTime, LocalDateTime newEndDateTime) {
        if (newStartDateTime == null) {
            newStartDateTime = this.startDateTime;
        }
        if (newEndDateTime == null) {
            newEndDateTime = this.endDateTime;
        }
        if (newStartDateTime.isAfter(newEndDateTime)) {
            throw new DomainValidationException("Start date cannot be after end date");
        }
        this.startDateTime = newStartDateTime;
        this.endDateTime = newEndDateTime;
    }

    private void validateInput(Long employeeId, Long plotId, HandlingCategory category, String description, LocalDateTime startDateTime, LocalDateTime endDateTime){
        if(employeeId == null) {
            throw new DomainValidationException("Employee ID cannot be null");
        } else if(plotId == null) {
            throw new DomainValidationException("Plot ID cannot be null");
        } else if(category == null) {
            throw new DomainValidationException("Category cannot be null");
        } else if(description == null || description.isBlank()) {
            throw new DomainValidationException("Description cannot be null or blank");
        } else if(startDateTime == null) {
            throw new DomainValidationException("Start date cannot be null");
        } else if(endDateTime == null) {
            throw new DomainValidationException("End date cannot be null");
        } else if (endDateTime.isBefore(startDateTime)) {
            throw new DomainValidationException("End date cannot be before start date");
        }
    }

}