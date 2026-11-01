package br.com.agroaqua.api.management.infrastructure.controller.plot;

import br.com.agroaqua.api.management.application.dto.plot.create.PlotCreateRequest;
import br.com.agroaqua.api.management.application.dto.plot.create.PlotCreateResponse;
import br.com.agroaqua.api.management.application.dto.plot.get.PlotGetResponse;
import br.com.agroaqua.api.management.application.dto.plot.patch.PlotPatchRequest;
import br.com.agroaqua.api.management.application.usecase.plot.FindPlotUseCase;
import br.com.agroaqua.api.management.application.usecase.plot.PatchPlotUseCase;
import br.com.agroaqua.api.management.application.usecase.plot.RegisterNewPlotUseCase;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plot")
public class PlotController {

    private final RegisterNewPlotUseCase registerNewPlotUseCase;
    private final FindPlotUseCase findPlotUseCase;
    private final PatchPlotUseCase patchPlotUseCase;

    public PlotController(RegisterNewPlotUseCase registerNewPlotUseCase, FindPlotUseCase findPlotUseCase, PatchPlotUseCase patchPlotUseCase) {
        this.registerNewPlotUseCase = registerNewPlotUseCase;
        this.findPlotUseCase = findPlotUseCase;
        this.patchPlotUseCase = patchPlotUseCase;
    }

    @PostMapping()
    public ResponseEntity<PlotCreateResponse> registerPlot(@Valid @RequestBody PlotCreateRequest request) {
        PlotCreateResponse response = registerNewPlotUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping()
    public ResponseEntity<Slice<PlotGetResponse>> findAllPlots(Pageable pageable) {
        Slice<PlotGetResponse> response = findPlotUseCase.findAll(pageable);
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
   public ResponseEntity<Slice<PlotGetResponse>> findPlotByCropId(@PathVariable Long cropId, Pageable pageable) {
        Slice<PlotGetResponse> response = findPlotUseCase.findByCropId(cropId, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(response);
   }

   @PatchMapping("/{id}")
    public ResponseEntity<PlotGetResponse> updatePlot(@PathVariable Long id, @Valid @RequestBody PlotPatchRequest request) {
        PlotGetResponse response = patchPlotUseCase.execute(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
   }
}