package br.com.agroaqua.api.management.domain.crop;

import java.math.BigDecimal;

public class Crop {
    private Long id;
    private String name;
    private String description;
    private BigDecimal normalHumidity;
    private int standardTimeToGrowInDays;

    public Crop(String name, String description, BigDecimal normalHumidity, int standartTimeToGrow) {
        validateInput(name, description, normalHumidity, standartTimeToGrow);
        this.name = name;
        this.description = description;
        this.normalHumidity = normalHumidity;
        this.standardTimeToGrowInDays = standartTimeToGrow;
    }

    public Crop(Long id, String name, String description, BigDecimal normalHumidity, int standartTimeToGrow) {
        this(name, description, normalHumidity, standartTimeToGrow);
        if(id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getStandardTimeToGrowInDays() {
        return standardTimeToGrowInDays;
    }

    public BigDecimal getNormalHumidity() {
        return normalHumidity;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void validateInput(String name, String description, BigDecimal normalHumidity, int standartTimeToGrow){
        if(name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        } else if(description == null) {
            throw new IllegalArgumentException("Description cannot be null");
        } else if(normalHumidity == null) {
            throw new IllegalArgumentException("NormalHumidity cannot be null");
        } else if(standartTimeToGrow <= 0) {
            throw new IllegalArgumentException("StandardTimeToGrowInDays cannot be negative or zero");
        }
    }
}