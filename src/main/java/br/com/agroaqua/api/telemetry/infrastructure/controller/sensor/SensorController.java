package br.com.agroaqua.api.telemetry.infrastructure.controller.sensor;

import br.com.agroaqua.api.telemetry.application.dto.sensor.create.SensorCreateRequest;
import br.com.agroaqua.api.telemetry.application.dto.sensor.create.SensorCreateResponse;
import br.com.agroaqua.api.telemetry.application.dto.sensor.get.SensorGetResponse;
import br.com.agroaqua.api.telemetry.application.dto.sensor.patch.SensorPatchRequest;
import br.com.agroaqua.api.telemetry.application.usecase.sensor.DeactivateSensorUseCase;
import br.com.agroaqua.api.telemetry.application.usecase.sensor.FindSensorUseCase;
import br.com.agroaqua.api.telemetry.application.usecase.sensor.PatchSensorUseCase;
import br.com.agroaqua.api.telemetry.application.usecase.sensor.RegisterNewSensorUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensor")
public class SensorController {

    private final RegisterNewSensorUseCase registerNewSensorUseCase;
    private final FindSensorUseCase findSensorUseCase;
    private final PatchSensorUseCase patchSensorUseCase;
    private final DeactivateSensorUseCase deactivateSensorUseCase;

    public SensorController(RegisterNewSensorUseCase registerNewSensorUseCase, FindSensorUseCase findSensorUseCase, PatchSensorUseCase patchSensorUseCase, DeactivateSensorUseCase deactivateSensorUseCase) {
        this.registerNewSensorUseCase = registerNewSensorUseCase;
        this.findSensorUseCase = findSensorUseCase;
        this.patchSensorUseCase = patchSensorUseCase;
        this.deactivateSensorUseCase = deactivateSensorUseCase;
    }

    @PostMapping()
    public ResponseEntity<SensorCreateResponse> registerSensor(@RequestBody SensorCreateRequest request) {
        SensorCreateResponse response = registerNewSensorUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping()
    public ResponseEntity<List<SensorGetResponse>> findAllSensors() {
        List<SensorGetResponse> response = findSensorUseCase.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorGetResponse> findById(@PathVariable Long id) {
        SensorGetResponse response = findSensorUseCase.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/code/{sensorCode}")
    public ResponseEntity<SensorGetResponse> findByCode(@PathVariable String sensorCode){
        SensorGetResponse response = findSensorUseCase.findByCode(sensorCode);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SensorGetResponse> updateSensor(@PathVariable Long id, @RequestBody SensorPatchRequest request) {
        SensorGetResponse response = patchSensorUseCase.execute(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<SensorGetResponse> deactivateSensor(@PathVariable Long id) {
        SensorGetResponse response = deactivateSensorUseCase.execute(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}