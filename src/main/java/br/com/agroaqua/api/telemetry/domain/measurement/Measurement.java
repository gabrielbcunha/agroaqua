package br.com.agroaqua.api.telemetry.domain.measurement;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Measurement {
    private Long id;
    private Long sensorId;
    private BigDecimal humidity;
    private LocalDateTime measuredAt;

    public Measurement(Long sensorId, BigDecimal humidity, LocalDateTime measuredAt) {
        validateInput(sensorId, humidity, measuredAt);
        this.sensorId = sensorId;
        this.humidity = humidity;
        this.measuredAt = measuredAt;
    }

    public Measurement(Long id, Long sensorId, BigDecimal humidity, LocalDateTime measuredAt) {
        this(sensorId, humidity, measuredAt);
        if(id == null){
            throw new IllegalArgumentException("id cannot be null");
        }
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getSensorId() {
        return sensorId;
    }

    public BigDecimal getHumidity() {
        return humidity;
    }

    public LocalDateTime getMeasuredAt() {
        return measuredAt;
    }

    public void validateInput(Long sensorId, BigDecimal humidity, LocalDateTime measuredAt) {
        if(sensorId == null){
            throw new IllegalArgumentException("Sensor Id can't be null");
        } else if(humidity == null){
            throw new IllegalArgumentException("Humidity can't be null");
        } else if(humidity.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Humidity must be greater than or equal zero");
        } else if (humidity.compareTo(new BigDecimal("100")) > 0) {
            throw new IllegalArgumentException("Humidity must be lower or equal to a hundred");
        }
        else if(measuredAt == null){
            throw new IllegalArgumentException("MeasuredAt can't be null");
        }
    }
}