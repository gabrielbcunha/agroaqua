package br.com.agroaqua.api.management.infrastructure.controller.plot;

import br.com.agroaqua.api.management.application.dto.plot.create.PlotCreateRequest;
import br.com.agroaqua.api.management.application.dto.plot.create.PlotCreateResponse;
import br.com.agroaqua.api.management.application.dto.plot.get.PlotGetResponse;
import br.com.agroaqua.api.management.application.usecase.plot.FindPlotUseCase;
import br.com.agroaqua.api.management.application.usecase.plot.RegisterNewPlotUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plot")
public class PlotController {

    private final RegisterNewPlotUseCase registerNewPlotUseCase;
    private final FindPlotUseCase findPlotUseCase;

    public PlotController(RegisterNewPlotUseCase registerNewPlotUseCase, FindPlotUseCase findPlotUseCase) {
        this.registerNewPlotUseCase = registerNewPlotUseCase;
        this.findPlotUseCase = findPlotUseCase;
    }

    @PostMapping()
    public ResponseEntity<PlotCreateResponse> registerPlot(@RequestBody PlotCreateRequest request) {
        PlotCreateResponse response = registerNewPlotUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping()
    public ResponseEntity<List<PlotGetResponse>> findAllPlots() {
        List<PlotGetResponse> response = findPlotUseCase.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlotGetResponse> findPlotById(@PathVariable Long id) {
        PlotGetResponse response = findPlotUseCase.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<PlotGetResponse> findPlotByCode(@PathVariable String code) {
        PlotGetResponse response = findPlotUseCase.findByCode(code);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

   @GetMapping("crop/{cropId}")
   public ResponseEntity<List<PlotGetResponse>> findPlotByCropId(@PathVariable Long cropId) {
        List<PlotGetResponse> response = findPlotUseCase.findByCropId(cropId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
   }
}