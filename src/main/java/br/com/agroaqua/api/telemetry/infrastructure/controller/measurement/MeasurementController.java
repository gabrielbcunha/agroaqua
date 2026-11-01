package br.com.agroaqua.api.telemetry.infrastructure.controller.measurement;

import br.com.agroaqua.api.telemetry.application.dto.measurement.create.MeasurementCreateRequest;
import br.com.agroaqua.api.telemetry.application.dto.measurement.create.MeasurementCreateResponse;
import br.com.agroaqua.api.telemetry.application.dto.measurement.get.MeasurementGetResponse;
import br.com.agroaqua.api.telemetry.application.usecase.measurement.FindMeasurementUseCase;
import br.com.agroaqua.api.telemetry.application.usecase.measurement.RegisterNewMeasurementUseCase;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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
            @Valid @RequestBody MeasurementCreateRequest request){
        MeasurementCreateResponse response = registerNewMeasurementUseCase.execute(request, apiKey);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping()
    public ResponseEntity<Slice<MeasurementGetResponse>> findAllMeasurements(Pageable pageable) {
        Slice<MeasurementGetResponse> response = findMeasurementUseCase.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeasurementGetResponse> findById(@PathVariable Long id) {
        MeasurementGetResponse response = findMeasurementUseCase.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/sensor/{sensorId}")
    public ResponseEntity<Slice<MeasurementGetResponse>> findBySensorId(@PathVariable Long sensorId, Pageable pageable) {
        Slice<MeasurementGetResponse> response = findMeasurementUseCase.findAllBySensorId(sensorId, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/sensor/code/{sensorCode}")
    public ResponseEntity<Slice<MeasurementGetResponse>> findBySensorCode(@PathVariable String sensorCode, Pageable pageable  ) {
        Slice<MeasurementGetResponse> response = findMeasurementUseCase.findBySensorCode(sensorCode, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}