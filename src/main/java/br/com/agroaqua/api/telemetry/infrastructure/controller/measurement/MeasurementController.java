package br.com.agroaqua.api.telemetry.infrastructure.controller.measurement;

import br.com.agroaqua.api.telemetry.application.dto.measurement.create.MeasurementCreateRequest;
import br.com.agroaqua.api.telemetry.application.dto.measurement.create.MeasurementCreateResponse;
import br.com.agroaqua.api.telemetry.application.usecase.measurement.RegisterNewMeasurementUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/measurement")
public class MeasurementController {

    private final RegisterNewMeasurementUseCase registerNewMeasurementUseCase;

    public MeasurementController(RegisterNewMeasurementUseCase registerNewMeasurementUseCase) {
        this.registerNewMeasurementUseCase = registerNewMeasurementUseCase;
    }

    @PostMapping()
    public ResponseEntity<MeasurementCreateResponse> registerMeasurement(
            @RequestHeader("X-Sensor-Key") String apiKey,
            @RequestBody MeasurementCreateRequest request){
        MeasurementCreateResponse response = registerNewMeasurementUseCase.execute(request, apiKey);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}