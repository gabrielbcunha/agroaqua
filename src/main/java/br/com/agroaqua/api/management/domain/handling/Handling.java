package br.com.agroaqua.api.management.domain.handling;

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
            throw new IllegalArgumentException("ID cannot be null");
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
            throw new IllegalArgumentException("employeeId cannot be null");
        }
        this.employeeId = employeeId;
    }

    public void changePlotId(Long newPlotId) {
        if (newPlotId == null) {
            throw new IllegalArgumentException("New Plot ID cannot be null");
        }
        this.plotId = newPlotId;
    }

    public void changeCategory(HandlingCategory newCategory) {
        if (newCategory == null) {
            throw new IllegalArgumentException("New Category cannot be null");
        }
        this.category = newCategory;
    }

    public void changeDescription(String newDescription) {
        if (newDescription == null) {
            throw new IllegalArgumentException("New Description cannot be null");
        }
        this.description = newDescription;
    }

    public void changeStartDateTime(LocalDateTime newStartDateTime) {
        if (newStartDateTime == null) {
            throw new IllegalArgumentException("New Start DateTime cannot be null");
        } else if (newStartDateTime.isAfter(endDateTime)) {
            throw new IllegalArgumentException("New Start DateTime cannot be after end DateTime");
        }
        this.startDateTime = newStartDateTime;
    }

    public void changeEndDateTime(LocalDateTime newEndDateTime) {
        if (newEndDateTime == null) {
            throw new IllegalArgumentException("New End DateTime cannot be null");
        } else if (newEndDateTime.isBefore(startDateTime)) {
            throw new IllegalArgumentException("New End DateTime cannot be before start DateTime");
        }
        this.endDateTime = newEndDateTime;
    }

    public void validateInput(Long employeeId, Long plotId, HandlingCategory category, String description, LocalDateTime startDateTime, LocalDateTime endDateTime){
        if(employeeId == null) {
            throw new IllegalArgumentException("Employee ID cannot be null");
        } else if(plotId == null) {
            throw new IllegalArgumentException("Plot ID cannot be null");
        } else if(category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        } else if(description == null) {
            throw new IllegalArgumentException("Description cannot be null");
        } else if(startDateTime == null) {
            throw new IllegalArgumentException("Start date cannot be null");
        } else if(endDateTime == null) {
            throw new IllegalArgumentException("End date cannot be null");
        } else if (endDateTime.isBefore(startDateTime)) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
    }

}