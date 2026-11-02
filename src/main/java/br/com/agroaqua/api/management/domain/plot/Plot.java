package br.com.agroaqua.api.management.domain.plot;

import br.com.agroaqua.api.shared.domain.exception.DomainValidationException;

import java.math.BigDecimal;

public class Plot {
    private Long id;
    private String code;
    private Long cropId;
    private BigDecimal totalArea;
    private BigDecimal usedArea;
    private BigDecimal availableArea;
    private BigDecimal width;
    private BigDecimal length;

    public Plot(String code, Long cropId, BigDecimal length, BigDecimal width, BigDecimal usedArea) {
        validadeInput(length, width, usedArea, code);
        this.code = code;
        this.cropId = cropId;
        this.length = length;
        this.width = width;
        this.totalArea = length.multiply(width);
        validateUsedArea(this.totalArea, usedArea);
        this.usedArea = usedArea;
        this.availableArea = this.totalArea.subtract(usedArea);
    }

    public Plot(Long id, String code, Long cropId, BigDecimal length, BigDecimal width, BigDecimal usedArea) {
        this(code, cropId, length, width, usedArea);
        if(id == null) {
            throw new DomainValidationException("Id cannot be null");
        }
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getLength() {
        return length;
    }

    public BigDecimal getAvailableArea() {
        return availableArea;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public BigDecimal getTotalArea() {
        return totalArea;
    }

    public String getCode() {
        return code;
    }

    public Long getCropId() {
        return cropId;
    }

    public BigDecimal getUsedArea() {
        return usedArea;
    }

    public void changeCode(String newCode) {
        if (newCode == null || newCode.isBlank()) {
            throw new DomainValidationException("New code cannot be null or blank");
        }
        this.code = newCode;
    }

    public void changeCropId(Long newCropId) {
        if (newCropId == null) {
            throw new DomainValidationException("New crop id cannot be null");
        }
        this.cropId = newCropId;
    }

    public void changeWidthLengthUsedArea(BigDecimal newWidth, BigDecimal newLength, BigDecimal newUsedArea) {
        if (newWidth == null) {
            newWidth = this.width;
        }
        if (newLength == null) {
            newLength = this.length;
        }
        if (newUsedArea == null) {
            newUsedArea = this.usedArea;
        }
        if (newWidth.compareTo(BigDecimal.ZERO) <= 0) {
            throw new DomainValidationException("New width cannot be negative or zero");
        }
        if (newLength.compareTo(BigDecimal.ZERO) <= 0) {
            throw new DomainValidationException("New length cannot be negative or zero");
        }
        if (newUsedArea.compareTo(BigDecimal.ZERO) < 0) {
            throw new DomainValidationException("New used area cannot be negative");
        }

        this.length = newLength;
        this.width = newWidth;

        BigDecimal newTotalArea = newLength.multiply(newWidth);
        this.totalArea = newTotalArea;

        if(newUsedArea.compareTo(newTotalArea) > 0) {
            throw new DomainValidationException("New used area cannot be greater than the total area");
        }
        this.usedArea = newUsedArea;

        this.availableArea = newTotalArea.subtract(newUsedArea);
    }

    private void validadeInput(BigDecimal length, BigDecimal width, BigDecimal usedArea, String code){
        if(length == null) {
            throw new DomainValidationException("Length cannot be null");
        } else if (length.compareTo(BigDecimal.ZERO) <= 0) {
            throw new DomainValidationException("Length cannot be negative or zero");
        }

        if(width == null) {
            throw new DomainValidationException("Width cannot be null");
        } else if (width.compareTo(BigDecimal.ZERO) <= 0) {
            throw new DomainValidationException("Width cannot be negative or zero");
        }

        if(usedArea == null) {
            throw new DomainValidationException("UsedArea cannot be null");
        } else if (usedArea.compareTo(BigDecimal.ZERO) < 0) {
            throw new DomainValidationException("UsedArea cannot be negative");
        }

        if(code == null || code.isBlank()) {
            throw new DomainValidationException("Code cannot be null or blank");
        }
    }

    private void validateUsedArea(BigDecimal totalArea, BigDecimal usedArea) {
        if (totalArea.compareTo(usedArea) < 0) {
            throw new DomainValidationException("Used Area cannot be greater than the total area");
        }
    }
}