package br.com.agroaqua.api.management.domain.crop;

import br.com.agroaqua.api.shared.domain.exception.DomainValidationException;

import java.math.BigDecimal;

public class Crop {
    private Long id;
    private String name;
    private String description;
    private BigDecimal normalHumidity;
    private int standardTimeToGrowInDays;

    public Crop(String name, String description, BigDecimal normalHumidity, int standardTimeToGrow) {
        validateInput(name, description, normalHumidity, standardTimeToGrow);
        this.name = name;
        this.description = description;
        this.normalHumidity = normalHumidity;
        this.standardTimeToGrowInDays = standardTimeToGrow;
    }

    public Crop(Long id, String name, String description, BigDecimal normalHumidity, int standardTimeToGrow) {
        this(name, description, normalHumidity, standardTimeToGrow);
        if(id == null) {
            throw new DomainValidationException("Id cannot be null");
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
        if (newName == null || newName.isBlank()) {
            throw new DomainValidationException("new Name cannot be null or blank");
        }
        this.name = newName;
    }

    public void changeDescription(String newDescription) {
        if (newDescription == null || newDescription.isBlank()) {
            throw new DomainValidationException("new Description cannot be null or blank");
        }
        this.description = newDescription;
    }

    public void changeNormalHumidity(BigDecimal newNormalHumidity) {
        if (newNormalHumidity == null) {
            throw new DomainValidationException("new NormalHumidity cannot be null");
        } else if (newNormalHumidity.compareTo(BigDecimal.ZERO) <= 0 || newNormalHumidity.compareTo(new BigDecimal("100")) > 0) {
            throw new DomainValidationException("NormalHumidity cannot be less than or equal to 0, nor greater than 100.");
        }
        this.normalHumidity = newNormalHumidity;
    }

    public void changeStandardTimeToGrowInDays(int newStandardTimeToGrowInDays) {
        if (newStandardTimeToGrowInDays < 0 ||  newStandardTimeToGrowInDays == 0) {
            throw new DomainValidationException("New Standard Time To Grow In Days cannot be negative or Zero");
        }
        this.standardTimeToGrowInDays = newStandardTimeToGrowInDays;
    }

    private void validateInput(String name, String description, BigDecimal normalHumidity, int standardTimeToGrow){
        if(name == null || name.isBlank()) {
            throw new DomainValidationException("Name cannot be null or blank");
        } else if(description == null || description.isBlank()) {
            throw new DomainValidationException("Description cannot be null or blank");
        } else if (normalHumidity == null) {
            throw new DomainValidationException("NormalHumidity cannot be null");
        } else if(normalHumidity.compareTo(BigDecimal.ZERO) <= 0 || normalHumidity.compareTo(new BigDecimal("100")) > 0) {
         throw new DomainValidationException("NormalHumidity cannot be less than or equal to 0, nor greater than 100.");
        }
        else if(standardTimeToGrow <= 0) {
            throw new DomainValidationException("StandardTimeToGrowInDays cannot be negative or zero");
        }
    }
}