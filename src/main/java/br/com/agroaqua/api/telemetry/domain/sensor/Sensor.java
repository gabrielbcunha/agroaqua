package br.com.agroaqua.api.telemetry.domain.sensor;

public class Sensor {
    private Long id;
    private String code;
    private Long plotId;
    private boolean active;

    public Sensor(String code, Long plotId, boolean active) {
        validadeInput(code, plotId);
        this.code = code;
        this.plotId = plotId;
        this.active = active;
    }

    public Sensor(Long id, String code, Long plotId, boolean active) {
        this(code, plotId, active);
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

    public void validadeInput(String code, Long plotId){
        if(code == null){
            throw new IllegalArgumentException("code cannot be null");
        } else if(plotId == null){
            throw new IllegalArgumentException("plotId cannot be null");
        }
    }

}