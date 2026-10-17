package br.com.agroaqua.api.management.domain.plot;

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
        this.usedArea = usedArea;
        this.availableArea = this.totalArea.subtract(usedArea);
    }

    public Plot(Long id, String code, Long cropId, BigDecimal length, BigDecimal width, BigDecimal usedArea) {
        this(code, cropId, length, width, usedArea);
        if(id == null) {
            throw new IllegalArgumentException("Id cannot be null");
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
        if (newCode == null) {
            throw new IllegalArgumentException("New code cannot be null");
        }
        this.code = newCode;
    }

    public void changeCropId(Long newCropId) {
        if (newCropId == null) {
            throw new IllegalArgumentException("New crop id cannot be null");
        }
        this.cropId = newCropId;
    }

    public void changeUsedArea(BigDecimal newUsedArea) {
        if (newUsedArea == null) {
            throw new IllegalArgumentException("New used area cannot be null");
        } else if (newUsedArea.compareTo(totalArea) > 0) {
            throw new IllegalArgumentException("New used area cannot be greater than total area");
        }
        this.usedArea = newUsedArea;
    }

    public void changeWidth(BigDecimal newWidth) {
        if (newWidth == null || newWidth.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("New width cannot be negative or zero");
        }
        this.width = newWidth;
    }

    public void changeLength(BigDecimal newLength) {
        if (newLength == null || newLength.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("New length cannot be negative or zero");
        }
        this.length = newLength;
    }

    private void validadeInput(BigDecimal length, BigDecimal width, BigDecimal usedArea, String code){
        if(length == null) {
            throw new IllegalArgumentException("Length cannot be null");
        } else if(width == null) {
            throw new IllegalArgumentException("Width cannot be null");
        } else if(usedArea == null) {
            throw new IllegalArgumentException("UsedArea cannot be null");
        } else if(code == null) {
            throw new IllegalArgumentException("Code cannot be null");
        }
    }
}