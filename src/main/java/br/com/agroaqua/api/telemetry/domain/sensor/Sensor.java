package br.com.agroaqua.api.telemetry.domain.sensor;

public class Sensor {
    private Long id;
    private String code;
    private Long plotId;
    private boolean active;
    private String apiKeyHash;

    public Sensor(String code, Long plotId, boolean active, String apiKeyHash) {
        validadeInput(code, plotId, apiKeyHash);
        this.code = code;
        this.plotId = plotId;
        this.active = active;
        this.apiKeyHash = apiKeyHash;
    }

    public Sensor(Long id, String code, Long plotId, boolean active, String apiKeyHash) {
        this(code, plotId, active, apiKeyHash);
        if(id == null){
            throw new IllegalArgumentException("id cannot be null");
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

    public void validadeInput(String code, Long plotId, String apiKeyHash) {
        if(code == null){
            throw new IllegalArgumentException("code cannot be null");
        } else if(plotId == null){
            throw new IllegalArgumentException("plotId cannot be null");
        } if(apiKeyHash == null){
            throw new IllegalArgumentException("apiKeyHash cannot be null");
        }
    }

}