package br.com.agroaqua.api.telemetry.infrastructure.controller.measurement;

import br.com.agroaqua.api.telemetry.application.dto.measurement.create.MeasurementCreateRequest;
import br.com.agroaqua.api.telemetry.application.dto.measurement.create.MeasurementCreateResponse;
import br.com.agroaqua.api.telemetry.application.dto.measurement.get.MeasurementGetResponse;
import br.com.agroaqua.api.telemetry.application.usecase.measurement.FindMeasurementUseCase;
import br.com.agroaqua.api.telemetry.application.usecase.measurement.RegisterNewMeasurementUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/measurement")
public class MeasurementController {

    private final RegisterNewMeasurementUseCase registerNewMeasurementUseCase;
    private final FindMeasurementUseCase findMeasurementUseCase;

    public MeasurementController(RegisterNewMeasurementUseCase registerNewMeasurementUseCase, FindMeasurementUseCase findMeasurementUseCase) {
        this.registerNewMeasurementUseCase = registerNewMeasurementUseCase;
        this.findMeasurementUseCase = findMeasurementUseCase;
    }

    @PostMapping()
    public ResponseEntity<MeasurementCreateResponse> registerMeasurement(
            @RequestHeader("X-Sensor-Key") String apiKey,
            @RequestBody MeasurementCreateRequest request){
        MeasurementCreateResponse response = registerNewMeasurementUseCase.execute(request, apiKey);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping()
    public ResponseEntity<List<MeasurementGetResponse>> findAllMeasurements() {
        List<MeasurementGetResponse> response = findMeasurementUseCase.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeasurementGetResponse> findById(@PathVariable Long id) {
        MeasurementGetResponse response = findMeasurementUseCase.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/sensor/{sensorId}")
    public ResponseEntity<List<MeasurementGetResponse>> findBySensorId(@PathVariable Long sensorId) {
        List<MeasurementGetResponse> response = findMeasurementUseCase.findAllBySensorId(sensorId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/sensor/code/{sensorCode}")
    public ResponseEntity<List<MeasurementGetResponse>> findBySensorCode(@PathVariable String sensorCode) {
        List<MeasurementGetResponse> response = findMeasurementUseCase.findBySensorCode(sensorCode);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}