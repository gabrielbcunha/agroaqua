package br.com.agroaqua.api.telemetry.infrastructure.controller.sensor;

import br.com.agroaqua.api.telemetry.application.dto.sensor.create.SensorCreateRequest;
import br.com.agroaqua.api.telemetry.application.dto.sensor.create.SensorCreateResponse;
import br.com.agroaqua.api.telemetry.application.usecase.sensor.RegisterNewSensorUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sensor")
public class SensorController {

    private final RegisterNewSensorUseCase registerNewSensorUseCase;

    public SensorController(RegisterNewSensorUseCase registerNewSensorUseCase) {
        this.registerNewSensorUseCase = registerNewSensorUseCase;
    }

    @PostMapping()
    public ResponseEntity<SensorCreateResponse> registerSensor(@RequestBody SensorCreateRequest request) {
        SensorCreateResponse response = registerNewSensorUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
