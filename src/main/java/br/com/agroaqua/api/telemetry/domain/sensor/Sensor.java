package br.com.agroaqua.api.telemetry.domain.sensor;

import br.com.agroaqua.api.shared.domain.exception.DomainValidationException;

public class Sensor {
    private Long id;
    private String code;
    private Long plotId;
    private boolean active;
    private String apiKeyHash;

    public Sensor(String code, Long plotId, boolean active, String apiKeyHash) {
        validateInput(code, plotId, apiKeyHash);
        this.code = code;
        this.plotId = plotId;
        this.active = active;
        this.apiKeyHash = apiKeyHash;
    }

    public Sensor(Long id, String code, Long plotId, boolean active, String apiKeyHash) {
        this(code, plotId, active, apiKeyHash);
        if(id == null){
            throw new DomainValidationException("id cannot be null");
        }
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public Long getPlotId() {
        return plotId;
    }

    public boolean isActive() {
        return active;
    }

    public String getApiKeyHash() {
        return apiKeyHash;
    }

    public void changeCode(String newCode) {
        if (newCode == null) {
            throw new DomainValidationException("newCode cannot be null");
        }
        this.code = newCode;
    }

    public void changePlotId(Long plotId) {
        if (plotId == null) {
            throw new DomainValidationException("plotId cannot be null");
        }
        this.plotId = plotId;
    }

    public void deactivate() {
        if (!this.active){
            throw new DomainValidationException("Sensor is already deactivated");
        }
        this.active = false;
    }

    private void validateInput(String code, Long plotId, String apiKeyHash) {
        if(code == null){
            throw new DomainValidationException("code cannot be null");
        } else if(plotId == null){
            throw new DomainValidationException("plotId cannot be null");
        } if(apiKeyHash == null){
            throw new DomainValidationException("apiKeyHash cannot be null");
        }
    }

}