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

    public void changeName(String newName) {
        if (newName == null || newName.trim().isEmpty()) {
            throw new IllegalArgumentException("new Name cannot be null or empty");
        }
        this.name = newName;
    }

    public void changeDescription(String newDescription) {
        if (newDescription == null || newDescription.trim().isEmpty()) {
            throw new IllegalArgumentException("new Description cannot be null or empty");
        }
        this.description = newDescription;
    }

    public void changeNormalHumidity(BigDecimal newNormalHumidity) {
        if (newNormalHumidity == null || newNormalHumidity.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("new NormalHumidity cannot be null or Zero");
        }
        this.normalHumidity = newNormalHumidity;
    }

    public void changeStandardTimeToGrowInDays(int newStandardTimeToGrowInDays) {
        if (newStandardTimeToGrowInDays < 0 ||  newStandardTimeToGrowInDays == 0) {
            throw new IllegalArgumentException("New Standard Time To Grow In Days cannot be negative or Zero");
        }
        this.standardTimeToGrowInDays = newStandardTimeToGrowInDays;
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